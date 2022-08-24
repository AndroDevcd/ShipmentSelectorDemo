package com.intuisoft.moderncalc.shipmentselector.ui.driverInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import com.intuisoft.moderncalc.shipmentselector.Constants.JobStatus.STATUS_ASSIGNED
import com.intuisoft.moderncalc.shipmentselector.Constants.JobStatus.STATUS_UNASSIGNED
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.BindingFragment
import com.intuisoft.moderncalc.shipmentselector.databinding.DriverInfoFragmentBinding
import com.intuisoft.moderncalc.shipmentselector.ui.AppDateTimeFormatter
import com.intuisoft.moderncalc.shipmentselector.ui.viewmodel.DriverManagementPortalViewModel
import kotlinx.android.synthetic.main.driver_info_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DriverInfoFragment : BindingFragment<DriverInfoFragmentBinding>() {

    private val viewModel: DriverManagementPortalViewModel by sharedViewModel()
    private val timeFormatter: AppDateTimeFormatter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentDriver?.let {
            binding.driver.text = "${it.firstName} ${it.lastName}"
            binding.jobStatus.text = "Job Status: ${getJobStatus(it.currentJob)}"

            if(getJobStatus(it.currentJob) == STATUS_ASSIGNED) {
                binding.assignJob.isVisible = false
            }

            viewModel.getCurrentJobInfo(it.currentJob)
        }

        viewModel.jobInfo.observe(viewLifecycleOwner, {
            binding.assignJob.isVisible = false
            binding.jobStatus.text = "Job Status: ${STATUS_ASSIGNED}"
            binding.jobLocation.text = "Delivering to: ${it.jobLocation}"
        })

        binding.assignJob.setOnClickListener {
            viewModel.assignJob()
        }
    }

    fun getJobStatus(currentJob: Int) =
        when(currentJob) {
            -1 -> STATUS_UNASSIGNED
            else -> STATUS_ASSIGNED
        }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}