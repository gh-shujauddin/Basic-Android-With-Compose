package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val scheduleDao: ScheduleDao) : BusScheduleRepository {
    override fun getAllSchedule(): Flow<List<BusSchedule>> = scheduleDao.getAllSchedule()

    override fun getSchedule(stopName: String): Flow<List<BusSchedule>> = scheduleDao.getSchedule(stopName = stopName)
}