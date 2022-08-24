package com.intuisoft.moderncalc.shipmentselector.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.SingleLiveData
import com.intuisoft.moderncalc.shipmentselector.data.DriverRepository
import com.intuisoft.moderncalc.shipmentselector.data.RegionManagerRepository
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class DriverManagementPortalViewModel(
    application: Application,
    val driverRepository: DriverRepository
) : BaseViewModel(application) {

    private val _genericError = MutableLiveData<Unit>()
    val genericError: LiveData<Unit> = _genericError

    private val _drivers = MutableLiveData<List<Driver>>()
    val drivers: LiveData<List<Driver>> = _drivers

    private val _jobInfo = SingleLiveData<JobRequest>()
    val jobInfo: LiveData<JobRequest> = _jobInfo

    var currentDriver: Driver? = null

    fun getAllDrivers() {
        execute({
            driverRepository.getAvailableDrivers()
        }) { regionInfo ->
            if(regionInfo.isSuccess) {
                regionInfo.getOrNull()?.let {
                    _drivers.postValue(it)
                }
            } else {
                _genericError.postValue(Unit)
            }
        }
    }

    fun getCurrentJobInfo(jobId: Int) {
        execute({
            driverRepository.getJobInfo(jobId)
        }) { regionInfo ->
            if(regionInfo.isSuccess) {
                regionInfo.getOrNull()?.let {
                    _jobInfo.postValue(it)
                }
            } else {
                _genericError.postValue(Unit)
            }
        }
    }


    fun assignJob() {
        currentDriver?.let { driver ->
            execute({
                driverRepository.getNextJob(driver.firstName!!, driver.lastName!!)
            }) { regionInfo ->
                if(regionInfo.isSuccess) {
                    regionInfo.getOrNull()?.let { job ->
                        driverRepository.assignJob(driver, job)
                        _jobInfo.postValue(job)
                    }
                } else {
                    _genericError.postValue(Unit)
                }
            }
        }
    }
}