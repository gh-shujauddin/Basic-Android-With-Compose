package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        var currentImage by remember { mutableStateOf(1) }

        when (currentImage) {
            1 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space1,
                    imageName = R.string.space1_name,
                    imageTitle = R.string.space1_title,
                    imageYear = R.string.space1_year,
                    onPrevClick = {
                        currentImage = 1
                    },
                    onNextClick = {
                        currentImage = 2
                    }
                )
            }

            2 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space2,
                    imageName = R.string.space2_name,
                    imageTitle = R.string.space2_title,
                    imageYear = R.string.space2_year,
                    onPrevClick = {
                        currentImage = 1
                    },
                    onNextClick = {
                        currentImage = 3
                    }
                )
            }

            3 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space3,
                    imageName = R.string.space3_name,
                    imageTitle = R.string.space3_title,
                    imageYear = R.string.space3_year,
                    onPrevClick = {
                        currentImage = 2
                    },
                    onNextClick = {
                        currentImage = 4
                    }
                )
            }

            4 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space4,
                    imageName = R.string.space4_name,
                    imageTitle = R.string.space4_title,
                    imageYear = R.string.space4_year,
                    onPrevClick = {
                        currentImage = 3
                    },
                    onNextClick = {
                        currentImage = 5
                    }
                )
            }

            5 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space6,
                    imageName = R.string.space5_name,
                    imageTitle = R.string.space5_title,
                    imageYear = R.string.space5_year,
                    onPrevClick = {
                        currentImage = 4
                    },
                    onNextClick = {
                        currentImage = 6
                    })
            }

            6 -> {
                MainScreen(
                    modifier = Modifier,
                    imageId = R.drawable.space7,
                    imageName = R.string.space6_name,
                    imageTitle = R.string.space6_title,
                    imageYear = R.string.space6_year,
                    onPrevClick = {
                        currentImage = 5
                    },
                    onNextClick = {
                        currentImage = 6
                    }
                )
            }
        }


    }
}

@Composable
fun MainScreen(
    modifier: Modifier,
    imageId: Int,
    imageTitle: Int,
    imageName: Int,
    imageYear: Int,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArtSpaceImage(imageId = imageId)
        Spacer(modifier = Modifier.height(16.dp))
        ArtWorkDescription(imageTitle = imageTitle, imageName = imageName, imageYear = imageYear)
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = onPrevClick, modifier = modifier.width(120.dp)) {
            Text(
                text = "Previous",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
        Button(onClick = onNextClick, modifier = modifier.width(120.dp)) {
            Text(
                text = "Next",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ArtSpaceImage(
    imageId: Int
) {

    Image(
        modifier = Modifier
            .height(600.dp)
            .border(
                BorderStroke(2.dp, Color.Gray)
            )
            .fillMaxWidth()
            .fillMaxHeight(),
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        alignment = Alignment.Center
    )

}

@Composable
fun ArtWorkDescription(
    imageTitle: Int,
    imageName: Int,
    imageYear: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(2.dp, Color.Gray))
            .padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = imageTitle),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraLight
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = imageName) + ", ")
                }
                append(stringResource(id = imageYear))
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}