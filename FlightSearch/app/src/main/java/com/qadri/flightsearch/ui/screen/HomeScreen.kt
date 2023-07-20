@file:OptIn(ExperimentalMaterial3Api::class)

package com.qadri.flightsearch.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qadri.flightsearch.R
import com.qadri.flightsearch.data.Airport

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: FlightSearchViewModel,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
//    var searchString by remember { mutableStateOf("") }
    val uiState = viewModel.flightUiState.collectAsState()
    val fakeData = listOf("ajkd", "dkj", "kjdkj")

    Row(modifier = modifier.fillMaxSize()) {
        Box(modifier = modifier.fillMaxWidth()) {
            Row {
                OutlinedTextField(
                    value = uiState.value.searchString,
                    onValueChange = {
                        uiState.value.searchString = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (uiState.value.searchString != "") {
                    LazyColumn(modifier = modifier.fillMaxWidth()) {
                        items(fakeData) { flightDetail ->
                            PopulatedSearch(
                                modifier = modifier,
                                flightDetail = flightDetail,
                                onCardClick = { onSearch(flightDetail) })
                        }
                    }
                }

            }
        }
        Text(text = stringResource(R.string.flights_from, "--"), modifier = modifier.padding(8.dp))
        val list = listOf<Airport>(
            Airport(1, "abc", "ABC", 59),
            Airport(2, "dkjk", "ADSFK", 36)
        )
        LazyColumn() {
            items(list) {
                FlightCard(airport = it, modifier = modifier)
            }
        }

    }

}

@Composable
fun FlightCard(airport: Airport, modifier: Modifier) {
    Row {
        Column {
            Text(text = "DEPART")
            Row {
                Text(text = airport.iataCode)
                Text(text = airport.name)
            }
            Text(text = "ARRIVIE")
            
        }
    }
}

@Composable
fun PopulatedSearch(modifier: Modifier, flightDetail: String, onCardClick: () -> Unit) {
    Card(onClick = onCardClick, modifier = modifier.fillMaxWidth()) {
        Text(text = flightDetail, fontSize = 16.sp)
    }
}
