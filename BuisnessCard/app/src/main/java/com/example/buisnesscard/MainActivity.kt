package com.example.buisnesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buisnesscard.ui.theme.BuisnessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuisnessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Card()
                }
            }
        }
    }
}

@Composable
fun Card() {
    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(top = 150.dp, bottom = 100.dp)) {
//            Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img_06691),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(200.dp)
                    .width(200.dp)
            )
//            }
//            Row() {
            Text(
                text = "Shujauddin Qadri",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
//            }
//            Row() {
            Text(
                text = "Android Developer",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
//            }
        }
        Column(Modifier.padding(start = 50.dp)) {
            Row(Modifier.height(25.dp)) {
                Icon(Icons.Rounded.Phone, contentDescription = null)
                Text(text = "+91 9997063730", modifier = Modifier.padding(start = 20.dp))
            }
            Row(Modifier.height(25.dp)) {
                Icon(Icons.Rounded.Email, contentDescription = null)
                Text(text = "mshuja.uq@gmail.com", modifier = Modifier.padding(start = 20.dp))
            }
            Row(Modifier.height(25.dp)) {
                Icon(Icons.Rounded.LocationOn, contentDescription = null)
                Text(text = "Almora, Uttarakhand", modifier = Modifier.padding(start = 20.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    BuisnessCardTheme {
        Card()
    }
}