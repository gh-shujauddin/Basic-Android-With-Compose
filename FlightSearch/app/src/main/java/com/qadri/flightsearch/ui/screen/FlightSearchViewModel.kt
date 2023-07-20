package com.qadri.flightsearch.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qadri.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlightSearchViewModel(
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {

    private var _flightUiState  = MutableStateFlow(FlightSearchUiState())
    val flightUiState = _flightUiState.asStateFlow()


    companion object {
        var Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchViewModel)
                FlightSearchViewModel(
                    application.flightSearchRepository
                )
            }
        }
    }
}

data class FlightSearchUiState(
    var searchString: String = "",
    var isFlightClicked: Boolean = false
)