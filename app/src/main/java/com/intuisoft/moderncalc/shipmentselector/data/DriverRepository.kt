package com.intuisoft.moderncalc.shipmentselector.data

import android.util.Log
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus
import com.intuisoft.moderncalc.shipmentselector.db.AppDatabase
import com.intuisoft.moderncalc.shipmentselector.db.dao.DriverDao
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest
import kotlinx.coroutines.delay
import java.util.*

class DriverRepository(
    val appDatabase: AppDatabase,
    val regionManagerRepository: RegionManagerRepository
) {

    suspend fun cacheAllAvailableDrivers(drivers: List<String>) {
        delay(250)
        appDatabase.driverDao().insertAll(*Driver.consume(drivers).toTypedArray())
    }

    suspend fun getAvailableDrivers() : List<Driver> {
        val fetchRegionData = appDatabase.jobRequestDao().all?.isEmpty() ?: true
                && appDatabase.driverDao().all?.isEmpty() ?: true

        if(fetchRegionData) {
            val regionData = regionManagerRepository.getAllRegionData()

            regionData?.let {
                cacheAllAvailableDrivers(it.drivers)
                regionManagerRepository.cacheAllShipmentJobs(it.shipments)
            }
        }

        return appDatabase.driverDao().all ?: listOf()
    }

    private fun getVowelCount(firstName: String, lastName: String): Int {
        var vowels = 0

        val name = "${firstName.toLowerCase()} ${lastName.toLowerCase()}"
        for (i in 0..name.length - 1) {
            val ch = name[i]
            if (ch == 'a' || ch == 'e' || ch == 'i'
                || ch == 'o' || ch == 'u') {
                ++vowels
            }
        }

        return vowels
    }

    suspend fun getNextJob(firstName: String, lastName: String) : JobRequest? {
        val jobs = appDatabase.jobRequestDao().getAllUnassignedJobs()
        val al = appDatabase.jobRequestDao().all
        var highestScore = 0f
        var bestMatch: JobRequest? = null

        jobs?.forEachIndexed { i, it ->
            val isEven = (it.jobLocation!!.length  % 2) == 0
            var ssMultiplier : Float = 1f

            if(isEven) {
                ssMultiplier = 1.5f
            }

            val score = ssMultiplier * getVowelCount(firstName, lastName)

            if(score > highestScore) {
                highestScore = score
                bestMatch = it
            }
        }

        return bestMatch
    }


    suspend fun getJobInfo(jobId: Int) : JobRequest? {

        if(jobId != -1) {
            val jobs = appDatabase.jobRequestDao().locateJobsWithId(jobId)

            return jobs.firstOrNull()
        }

        return null
    }

    suspend fun assignJob(driver: Driver, job: JobRequest) {
        driver.currentJob = job.jobId ?: -1
        driver.updateTime = Calendar.getInstance().time

        job.status = JobStatus.ASSIGNED
        job.startedTime = driver.updateTime

        appDatabase.driverDao().updateDriver(driver)
        appDatabase.jobRequestDao().updateJob(job)
    }

}