package com.intuisoft.moderncalc.shipmentselector.db.dao

import androidx.room.*
import com.intuisoft.moderncalc.shipmentselector.data.model.JobStatus
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest


@Dao
interface DriverDao {
    @get:Query("SELECT * FROM Driver")
    val all: List<Driver>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg drivers: Driver?)

    @Update
    fun updateDriver(song: Driver)

    @Delete
    fun delete(driver: Driver?)
}