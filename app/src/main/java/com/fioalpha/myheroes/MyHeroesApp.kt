package com.fioalpha.myheroes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fioalpha.character.presentation.ui.CharactersScreen


@Composable
fun MyHeroesApp() {
    val navController = rememberNavController()
}

@Composable
fun MyHeroesNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            CharactersScreen()
        }
    }
}