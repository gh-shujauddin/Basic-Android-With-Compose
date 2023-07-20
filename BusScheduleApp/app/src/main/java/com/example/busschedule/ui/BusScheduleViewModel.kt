/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class BusScheduleUiState(
    val allSchedule: List<BusSchedule> = listOf()
)

data class RouteUiState(
    val allSchedule: List<BusSchedule> = listOf()
)

class BusScheduleViewModel(private val busScheduleRepository: BusScheduleRepository) : ViewModel() {

    val _scheduleState: StateFlow<BusScheduleUiState> = busScheduleRepository.getAllSchedule()
        .map { BusScheduleUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = BusScheduleUiState()
        )

    val getScheduleFor: StateFlow<BusScheduleUiState> = busScheduleRepository.getAllSchedule()
        .map { BusScheduleUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = BusScheduleUiState()
        )


    // Get example bus schedule
    fun getFullSchedule(): Flow<List<BusSchedule>> = flowOf(
        listOf(
            BusSchedule(
                1,
                "Example Street",
                0
            )
        )
    )

    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busScheduleRepository.getSchedule(stopName)

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)

                BusScheduleViewModel(
                    application.container.busScheduleRepository
                )
            }
        }
    }
}
