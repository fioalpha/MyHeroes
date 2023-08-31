package com.fioalpha.myheroes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import com.fioalpha.character.presentation.ui.CharacterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    @get: Rule val composeRule = createComposeRule()

    @Test
    fun t() {
        ActivityScenario.launch(CharacterActivity::class.java).use {
            scenario -> scenario.onActivity {
                activity ->
                composeRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
                Thread.sleep(200)
                composeRule.onNodeWithText("3-D Man").assertIsDisplayed()
                Thread.sleep(100)
                activity.recreate()
                composeRule.onNodeWithContentDescription("Loading").assertDoesNotExist()
                Thread.sleep(100)
            }
        }
    }
}