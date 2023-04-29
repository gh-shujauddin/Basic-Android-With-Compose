@file:OptIn(ExperimentalAnimationApi::class)

package com.qadri.jetpackcomposein30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qadri.jetpackcomposein30days.model.TaskRepository
import com.qadri.jetpackcomposein30days.model.Tasks
import com.qadri.jetpackcomposein30days.ui.theme.JetpackComposeIn30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeIn30DaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TaskAppScreen()
                }
            }
        }
    }
}

@Composable
fun TaskAppScreen() {
    Scaffold(
        topBar = {
            topAppBar()
        }
    ) {
        val tasks = TaskRepository.Tasks
        TaskList(task = tasks, modifier = Modifier.padding(it))
    }
}

@Composable
fun topAppBar() {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.onPrimary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItems(
    modifier: Modifier = Modifier,
    tasks: Tasks
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.medium),
        elevation = 4.dp,
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(
                text = stringResource(id = tasks.dayRes),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp)
            )

            Text(
                text = stringResource(id = tasks.taskRes),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary
            )
            Image(
                painter = painterResource(id = tasks.imgRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(320.dp)
                    .padding(16.dp)
            )
            if (expanded) {
                Text(
                    text = stringResource(id = tasks.descRes),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    task: List<Tasks>
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyColumn() {
            itemsIndexed(task) { index, tasks ->
                TaskItems(
                    tasks = tasks,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1)}
                            )
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeIn30DaysTheme {
        TaskAppScreen()
    }
}