package com.fioalpha.ui.components.heroes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fioalpha.ui.components.heroes.model.CharacterView


@Composable
fun ItemCharacterContainer(
    characters: List<CharacterView> = emptyList(),
    isLoading: Boolean = false,
    loadMore: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        ItemsCharacters(characters, loadMore = loadMore)
        if (!isLoading) return
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .background(Color(0XCCCCCCCC))
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(8.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp)
                )
            }

        }
    }

}

@Composable
@Preview
fun ItemCharacterContainerPreview() {
    ItemCharacterContainer(
       listOf(
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
            CharacterView("sdfsdf", "123434"),
    ) ){}
}