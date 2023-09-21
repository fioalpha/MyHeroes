package com.fioalpha.ui.components.heroes

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fioalpha.ui.components.heroes.model.CharacterView
import com.fioalpha.ui.theme.HeroesTheme

@Composable
fun PageContainer(
    title: String,
    content: @Composable () -> Unit,
) {
    HeroesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = { Text(text = title) }
                )
            }
        ) {
            it.calculateTopPadding()
            content.invoke()
        }
    }
}

@Preview()
@Composable
fun PageContainerPreview() {
    PageContainer("wefwerfwer",) {
        ItemsCharacters(
            listOf(
                CharacterView("sdfsdf", "123434"),
                CharacterView("sdfsdf", "123434"),
                CharacterView("sdfsdf", "123434"),
                CharacterView("sdfsdf", "123434"),
                CharacterView("sdfsdf", "123434"),
            )
        )
    }
}