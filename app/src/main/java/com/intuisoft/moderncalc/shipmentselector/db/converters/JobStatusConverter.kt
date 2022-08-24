package com.intuisoft.moderncalc.shipmentselector.db.converters

import androidx.room.TypeConverter
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus

class JobStatusConverter {
    @TypeConverter
    fun fromJobStatus(status: JobStatus): Int {
        return status.id
    }

    @TypeConverter
    fun toJobStatus(statusId: Int): JobStatus {
        return JobStatus.values().firstOrNull { it.id == statusId } ?: JobStatus.NO_DRIVER_ASSIGNED
    }
}