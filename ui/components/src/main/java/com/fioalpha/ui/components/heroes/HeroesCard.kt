package com.fioalpha.ui.components.heroes

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fioalpha.ui.components.R
import com.fioalpha.ui.theme.HeroesTheme
import okhttp3.internal.threadName

@Composable
fun ItemCharacter(
    path: String,
    description: String? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(220.dp),
    ) {
        AsyncImage(
            contentScale = ContentScale.FillWidth,
            model = path,
            contentDescription = description,
            placeholder = painterResource(id = R.drawable.marvel_placeholder),
            error = painterResource(id = R.drawable.marvel_placeholder)
        )
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0x99CCCCCC)
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = description?: "Unknown",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview()
@Composable
fun ItemCharacterPreview() {
    ItemCharacter("https://assets-prd.9ignimgs.com/2022/05/13/marvelheroes-1652464940086.jpg")
}

@Composable
fun ItemsCharacters(
    characters: List<CharacterView> = emptyList()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(characters) {
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                ItemCharacter(path = it.pathImage, it.name)
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

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
       CircularProgressIndicator()
    }
}

@Preview()
@Composable
fun LoadingPreview() {
    Loading()
}

@Composable
fun PageContainer(
    title: String,
    content: @Composable () -> Unit,
) {
    HeroesTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = { Text(text = title)}
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
    PageContainer("wefwerfwer", ) {
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

data class CharacterView(
    val pathImage: String,
    val name: String?

)