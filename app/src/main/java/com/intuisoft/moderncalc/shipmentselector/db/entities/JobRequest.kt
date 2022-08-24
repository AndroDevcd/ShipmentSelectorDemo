package com.intuisoft.moderncalc.shipmentselector.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus
import kotlinx.coroutines.Job
import java.util.*

@Entity
class JobRequest {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "location")
    var jobLocation: String? = null

    @ColumnInfo(name = "status")
    var status: JobStatus = JobStatus.NO_DRIVER_ASSIGNED

    @ColumnInfo(name = "id")
    var jobId: Int? = null

    @ColumnInfo(name = "startedTime")
    var startedTime: Date? = null

    companion object {
        fun consume(shipments: List<String>) =
            shipments.mapIndexed { i, shipment ->
                val request = JobRequest()
                request.jobId = i;
                request.jobLocation = shipment;

                request
            }
    }
}