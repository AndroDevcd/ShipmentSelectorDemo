package com.intuisoft.moderncalc.shipmentselector.ui.managementPortal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.intuisoft.moderncalc.shipmentselector.Constants
import com.intuisoft.moderncalc.shipmentselector.R
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.BindingFragment
import com.intuisoft.moderncalc.shipmentselector.databinding.DriverManagementPortalFragmentBinding
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.toArrayList
import com.intuisoft.moderncalc.shipmentselector.ui.AppDateTimeFormatter
import com.intuisoft.moderncalc.shipmentselector.ui.viewmodel.DriverManagementPortalViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DriverManagementPortalFragment : BindingFragment<DriverManagementPortalFragmentBinding>() {

    private val viewModel: DriverManagementPortalViewModel by sharedViewModel()
    private val timeFormatter: AppDateTimeFormatter by inject()

    val adapter = BasicDriverInfoListAdapter(
        onDriverSelected = ::onDriverSelected,
        timeFormatter = timeFormatter
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverManagementPortalFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllDrivers()

        binding.driversList.adapter = adapter
        viewModel.drivers.observe(viewLifecycleOwner, {
            adapter.addDrivers(it.toArrayList())
        })
    }

    fun onDriverSelected(driver: Driver) {
        viewModel.currentDriver = driver
        findNavController().navigate(
            R.id.driverManagementPortalFragment_to_driverInfoFragment, null, Constants.Navigation.ANIMATED_ENTER_EXIT_RIGHT_NAV_OPTION)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}