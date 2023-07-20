@OptIn(ExperimentalMaterial3Api::class)

package com.qadri.flightsearch

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qadri.flightsearch.ui.screen.FlightSearchViewModel
import com.qadri.flightsearch.ui.screen.HomeScreen

@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
                title = {
                    Text(text = "Flight Search")
                }
            )
        }
    ) {innerPadding ->
        val viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.Factory)
        HomeScreen(
            modifier = modifier.padding(innerPadding),
            viewModel = viewModel,
            onValueChange = {},
            onSearch = {
                viewModel.flightUiState.value.isFlightClicked = true
            }
        )
    }
}

