package com.intuisoft.moderncalc.shipmentselector.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.intuisoft.moderncalc.shipmentselector.db.converters.DateConverter
import com.intuisoft.moderncalc.shipmentselector.db.converters.JobStatusConverter
import com.intuisoft.moderncalc.shipmentselector.db.dao.DriverDao
import com.intuisoft.moderncalc.shipmentselector.db.dao.JobRequestDao
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest

@TypeConverters(DateConverter::class, JobStatusConverter::class)
@Database(entities = [Driver::class, JobRequest::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun driverDao(): DriverDao
    abstract fun jobRequestDao(): JobRequestDao
}