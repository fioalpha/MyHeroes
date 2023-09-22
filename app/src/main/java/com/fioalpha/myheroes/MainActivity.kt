package com.fioalpha.myheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.fioalpha.character.presentation.CharacterViewModel
import com.fioalpha.character.presentation.ui.CharactersScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: ComponentActivity() {
    private val viewModel by viewModel<CharacterViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent{
            CharactersScreen(viewModel)
        }

    }
}