package com.intuisoft.moderncalc.shipmentselector.data

import android.app.Application
import com.google.gson.Gson
import com.intuisoft.moderncalc.shipmentselector.R
import com.intuisoft.moderncalc.shipmentselector.data.model.RegionInfo
import com.intuisoft.moderncalc.shipmentselector.db.AppDatabase
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest
import kotlinx.coroutines.delay


class RegionManagerRepository(
    val application: Application,
    val appDatabase: AppDatabase
) {
    suspend fun getAllRegionData() : RegionInfo? {
        delay(500)
        val payload: String = application.resources.openRawResource(R.raw.shipment_payload)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        return gson.fromJson(payload, RegionInfo::class.java)
    }

    suspend fun cacheAllShipmentJobs(shipments: List<String>) {
        delay(250)
        appDatabase.jobRequestDao().insertAll(*JobRequest.consume(shipments).toTypedArray())
    }
}