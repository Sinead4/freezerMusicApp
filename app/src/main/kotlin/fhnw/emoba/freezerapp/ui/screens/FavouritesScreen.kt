package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.data.Song
import fhnw.emoba.freezerapp.model.FreezerModel



@Composable
    fun FavouritesScreen(model: FreezerModel) {
        Box(contentAlignment = Alignment.Center,
            modifier         = Modifier.fillMaxSize()
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()) {
                Text(text = "My Favorites", fontSize = 30.sp,
                    modifier = Modifier.padding(5.dp))
                FavoriteList(model)
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)){
                PlayBar(model = model)
            }

        }
    }

@Composable
fun FavoriteList(model: FreezerModel){
    model.songLists = emptyList()
    model.radioTrackList = emptyList()
    with(model){
        LazyColumn{
                items(favoriteList){
                    SongCard(song = it, model)
        }
    }
}
}

// if the FavButton is clicked in the Favorite Section again it will be removed
@Composable
fun FavButton(model: FreezerModel, song: Song) {
    with(model) {
        if (!song.isFavorite) {
            IconButton(
                onClick = { addToFavorites(song) },
                modifier = Modifier
                    .background(
                        SolidColor(Color(9, 162, 121)),
                        shape = CircleShape
                    )
                    .padding(1.dp)
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = "Heart")
            }
        } else if (song.isFavorite) {
            IconButton(
                onClick = { addToFavorites(song) },
                modifier = Modifier
                    .background(
                        SolidColor(Color(12, 105, 80)),
                        shape = CircleShape
                    )
                    .padding(1.dp)
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = "Play")
            }
        }

    }
}