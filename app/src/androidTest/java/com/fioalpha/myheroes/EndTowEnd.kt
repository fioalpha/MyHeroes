package com.fioalpha.myheroes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fioalpha.character.presentation.ui.CharacterActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndTowEnd {

    @get: Rule
    val composeRule = createAndroidComposeRule<CharacterActivity>()

    @Test
    fun firstTest() {
        composeRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
        Thread.sleep(100)
        composeRule.onNodeWithText("3-D Man").assertIsDisplayed()
    }

}