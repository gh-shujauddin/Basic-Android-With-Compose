package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

//This extension function allows you to reduce the amount of code you write when finding a UI component by its string resource. Instead of writing this:
//      composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.my_string)
//  You can now use the following instruction:
//      composeTestRule.onNodeWithStringId(R.string.my_string)