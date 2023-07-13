package com.qadri.marvelcomics.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qadri.marvelcomics.R
import com.qadri.marvelcomics.ui.screen.HomeScreen
import com.qadri.marvelcomics.ui.screen.MarvelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelComicsApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarvelAppBar(scrollBehaviour = scrollBehavior) }

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val marvelViewModel: MarvelViewModel = viewModel(factory = MarvelViewModel.Factory)
            HomeScreen(
                marvelUiState = marvelViewModel.marvelUiState,
                retryAction = marvelViewModel::getMarvelComicsData
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelAppBar(
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehaviour,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
    )
}