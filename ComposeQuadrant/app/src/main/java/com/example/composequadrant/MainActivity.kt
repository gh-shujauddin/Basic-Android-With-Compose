package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FinalQuadrant()
                }
            }
        }
    }
}

@Composable
fun FinalQuadrant() {
    Column(modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.weight(1f)) {

                Quadrant(heading = "Text Composable",
                    description = stringResource(id = R.string.textDesc),
                    bg = Color.Green,
                    modifier = Modifier.weight(1f)
                )

                Quadrant(heading = "Image Composable",
                    description = stringResource(id = R.string.imageDesc),
                    bg = Color.Yellow,
                    modifier = Modifier.weight(1f)
                )

        }
        Row(modifier = Modifier.weight(1f)) {
                Quadrant(heading = "Row Composable",
                    description = stringResource(id = R.string.rowDesc),
                    bg = Color.Cyan,
                    modifier = Modifier.weight(1f)
                )

                Quadrant(heading = "Column Composable",
                    description = stringResource(id = R.string.colDesc),
                    bg = Color.LightGray,
                    modifier = Modifier.weight(1f)
                )
        }
    }
}

@Composable
fun Quadrant(heading: String,
             description: String,
             bg: Color,
             modifier: Modifier = Modifier) {
        Column (modifier = modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = heading,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = description,
                textAlign = TextAlign.Justify
               )
        }
}


@Preview(showBackground = true)
@Composable
fun FinalQuadrantPreview() {
    ComposeQuadrantTheme {
        FinalQuadrant()
    }
}