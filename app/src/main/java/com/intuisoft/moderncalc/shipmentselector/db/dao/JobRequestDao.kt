package com.intuisoft.moderncalc.shipmentselector.db.dao

import androidx.room.*
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.db.entities.JobRequest


@Dao
interface JobRequestDao {
    @get:Query("SELECT * FROM JobRequest")
    val all: List<JobRequest>?

    @Query("SELECT * FROM JobRequest WHERE id == :jobId")
    fun locateJobsWithId(jobId: Int): List<JobRequest>

    @Query("SELECT * FROM JobRequest WHERE status == 0")
    fun getAllUnassignedJobs(): List<JobRequest>

    @Update
    fun updateJob(job: JobRequest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg jobs: JobRequest?)

    @Delete
    fun delete(job: JobRequest)
}