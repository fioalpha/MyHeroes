package com.fioalpha.character.presentation.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.fioalpha.character.presentation.CharacterInteraction
import com.fioalpha.character.presentation.CharacterViewState
import com.fioalpha.ui.components.heroes.CharacterView
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class CharactersPageStatesTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pageCharacterWithStateLoading() {
        composeTestRule.setContent {
            CharactersPageState(CharacterViewState.Loading) {}
        }
        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun pageCharacterWithStateDataWithTwoItems() {
        composeTestRule.setContent {
            CharactersPageState(CharacterViewState.Data(
                listOf(
                    CharacterView("Any Path", "First"),
                    CharacterView("Any Path", "Second"),
                )
            )) {}
        }
        composeTestRule.onNodeWithText("First").assertIsDisplayed()
        composeTestRule.onNodeWithText("Second").assertIsDisplayed()
    }

    @Test
    fun pageCharacterWithStateInitStateTransaction() {
        composeTestRule.setContent {
            CharactersPageState(CharacterViewState.Init) {
                assertTrue(it is CharacterInteraction.LoadingData)
            }
        }
    }
}
