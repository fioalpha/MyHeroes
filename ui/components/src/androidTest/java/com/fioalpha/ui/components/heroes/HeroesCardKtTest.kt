package com.fioalpha.ui.components.heroes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HeroesCardKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun itemCharacterTest() {
        composeTestRule.setContent {
            ItemsCharacters(
                listOf(
                    CharacterView("Any path", "Test")
                )
            )
        }
        composeTestRule.onNodeWithText("Test").assertIsDisplayed()
    }

}