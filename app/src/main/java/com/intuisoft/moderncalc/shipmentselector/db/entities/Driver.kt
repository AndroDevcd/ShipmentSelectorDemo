package com.intuisoft.moderncalc.shipmentselector.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus
import java.util.*


@Entity
class Driver {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    @ColumnInfo(name = "current_job")
    var currentJob: Int = -1

    @ColumnInfo(name = "update_time")
    var updateTime: Date = Date()

    companion object {
        fun consume(drivers: List<String>) =
            drivers.mapIndexed { i, worker ->
                val driver = Driver()
                val (firstName, lastName) = worker.split(" ")
                driver.firstName = firstName
                driver.lastName = lastName
                driver.updateTime = Calendar.getInstance().time

                driver
            }
    }
}