package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getAllSchedule(): Flow<List<BusSchedule>>

    fun getSchedule(stopName: String): Flow<List<BusSchedule>>
}