package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonTree(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
fun LemonTree(modifier: Modifier = Modifier) {

    var currentStep by remember { mutableStateOf(1) }
    var kuchhBhi by remember { mutableStateOf(0) }

    when (currentStep) {

        1 -> {
            LemonTextAndImage(
                modifier = modifier,
                text = R.string.picking_lemon,
                img = R.drawable.lemon_tree,
                contentDesc = R.string.lemon_tree,
                onImageClick = {
                    currentStep = 2
                    kuchhBhi = (2..4).random()
                }
            )
        }

        2 -> {
            LemonTextAndImage(
                modifier = modifier,
                text = R.string.tap_lemon,
                img = R.drawable.lemon_squeeze,
                contentDesc = R.string.tap_lemon,
                onImageClick = {
                    kuchhBhi--
                    if (kuchhBhi == 0)
                        currentStep = 3
                }
            )
        }

        3 -> {
            LemonTextAndImage(
                modifier = modifier,
                text = R.string.drink_lemonade,
                img = R.drawable.lemon_drink,
                contentDesc = R.string.lemonade_glass,
                onImageClick = {
                    currentStep = 4
                }
            )
        }

        4 -> {
            LemonTextAndImage(
                modifier = modifier,
                text = R.string.start_again,
                img = R.drawable.lemon_restart,
                contentDesc = R.string.lemon_tree,
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    modifier: Modifier = Modifier,
    text: Int,
    img: Int,
    contentDesc: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = text),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = img),
            contentDescription = stringResource(contentDesc),
            modifier = Modifier
                .wrapContentSize()
                .border(width = 1.dp, Color(105, 205, 206))
                .clickable(onClick = onImageClick)
                .padding(16.dp)
        )
    }
}
