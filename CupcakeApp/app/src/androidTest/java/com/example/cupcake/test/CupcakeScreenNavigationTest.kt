package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@get:Rule
val composeTestRule = createAndroidComposeRule<ComponentActivity>()

private lateinit var navController: TestNavHostController

@Before
fun setupCupcakeNavHost() {
    composeTestRule.setContent {
        navController = TestNavHostController(LocalContext.current).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
        CupcakeApp(navController = navController)
    }
}

@Test
fun cupcakeNavHost_verifyStartDestination() {
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
    val backTest = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backTest).assertDoesNotExist() //to assert if the back button exists or not
}

@Test
fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake).performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
}

private fun navigateToFlavorScreen() {
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake).performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate).performClick()
}

private fun navigateToPickupScreen() {
    navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).performClick()
}

private fun navigateToSummaryScreen() {
    navigateToPickupScreen()
    composeTestRule.onNodeWithText(getFormattedDate()).performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).performClick()
}

private fun getFormattedDate() : String {
    val calender = Calendar.getInstance()
    calender.add(java.util.Calendar.DATE, 1)
    val formattedDate = SimpleDateFormat("E MMM d", Locale.getDefault())
    return formattedDate.format(calender.time)
}

private fun performNavigateUp() {
    val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).performClick()
}

//Navigating to the Start screen by clicking the Up button from the Flavor screen
@Test
fun cupcakeNavHost_clickUpButtonInFlavorScreen_navigatesToStartScreen() {
    navigateToFlavorScreen()
    performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

//Navigating to the Start screen by clicking the Cancel button from the Flavor screen
@Test
fun cupcakeNavHost_clickCancelOnFlavorScreen_navigatesToStartScreen() {
    navigateToFlavorScreen()
    val cancelText = composeTestRule.activity.getString(com.example.cupcake.R.string.cancel)
    composeTestRule.onNodeWithContentDescription(cancelText).performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

//Navigating to the Pickup screen
@Test
fun cupcakeNavHost_navigatesToPickupScreen() {
    navigateToPickupScreen()
    navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
}

//Navigating to the Flavor screen by clicking the Up button from the Pickup screen
@Test
fun cupcakeNavHost_clickUpButtonInPickup_navigatesToFlavorScreen() {
    navigateToPickupScreen()
    performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
}

//Navigating to the Start screen by clicking the Cancel button from the Pickup screen
@Test
fun cupcakeNavHost_clickCancelOnPickupScreen_navigatesToStartScreen() {
    navigateToPickupScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel).performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

//Navigating to the Summary screen
@Test
fun cupcakeNavHost_navigateToSummaryScreen() {
    navigateToSummaryScreen()
    navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
}

//Navigating to the Start screen by clicking the Cancel button from the Summary screen
@Test
fun cupcakeNavHost_clickCancelOnSummaryScreen_navigatesToStartScreen() {
    navigateToSummaryScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel).performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}