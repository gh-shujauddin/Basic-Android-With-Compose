package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("select * from Schedule order by arrival_time")
    fun getAllSchedule(): Flow<List<BusSchedule>>

    @Query("select * from Schedule where stop_name = :stopName order by arrival_time")
    fun getSchedule(stopName: String): Flow<List<BusSchedule>>
}