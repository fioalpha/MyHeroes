package com.fioalpha.ui.components.heroes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fioalpha.ui.components.heroes.model.CharacterView

@Composable
fun ItemsCharacters(
    characters: List<CharacterView> = emptyList(),
    loadMore: (Int) -> Unit = {}
) {
    val lazyListState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyListState,
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(characters) {
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                ItemCharacter(path = it.pathImage, it.name)
            }
            InfiniteListHandler(lazyListState = lazyListState) {
                loadMore(characters.size)
            }
        }
    }
}

@Preview()
@Composable
fun ItemsCharactersPreview() {
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
