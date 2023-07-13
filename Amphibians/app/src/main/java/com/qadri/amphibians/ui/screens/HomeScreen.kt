package com.qadri.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.qadri.amphibians.R
import com.qadri.amphibians.model.AmphibianData

@Composable
fun HomeScreen(
    retryAction: () -> Unit,
    amphibianUiState: AmphibianUiState
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxWidth())
        }

        is AmphibianUiState.Success -> {
            AmphibianList(
                amphibian = amphibianUiState.amphibians,
                modifier = Modifier.fillMaxWidth()
            )
        }

        is AmphibianUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            Modifier.fillMaxWidth()
        )
    }

}


@Composable
fun AmphibianList(
    amphibian: List<AmphibianData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(amphibian, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianCard(
                amphibian = amphibian
            )
        }
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        modifier = modifier.size(200.dp),
        contentDescription = "Loading"
    )
}

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    amphibian: AmphibianData
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Text(
                    text = stringResource(R.string.title, amphibian.name, amphibian.type),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            AmphibianImage(title = amphibian.name, imgSrc = amphibian.imgSrc, modifier = modifier)
            Text(
                text = amphibian.description, style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }

}

@Composable
fun AmphibianImage(
    title: String,
    imgSrc: String, modifier: Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = title,
        contentScale = ContentScale.FillWidth,
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        modifier = modifier.fillMaxWidth()
    )
}



    
