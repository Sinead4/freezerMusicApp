package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.data.Song

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(model: FreezerModel) {
    Box(contentAlignment = Alignment.Center,
        modifier         = Modifier.fillMaxSize()
    ){  Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()) {
            SearchBar(model)
            ResultList(model)
    }
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)){
            PlayBar(model = model)
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(model: FreezerModel){
    val keyboard = LocalSoftwareKeyboardController.current
    with(model) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = searchInput,
                onValueChange = { searchInput = it},
                modifier = Modifier.padding(10.dp),
//                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )
            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = { keyboard?.hide()
                loadSongByNameAsync(searchInput)

            }, Modifier.background(Color(152,35,136)),
                content = { Text(text = "search")})

            Spacer(modifier = Modifier.height(15.dp))

            SearchChoseBar(model)

            Spacer(modifier = Modifier.height(15.dp))

            when{
                isLoading -> Text(text = "Es wird geladen")
            }
        }

    }
}


@Composable
fun ResultList(model: FreezerModel){
    with(model){
        LazyColumn{
            if(!songLists.isEmpty())
            items(songLists){
                SongCard(song = it, model)
            } else if(!albumList.isEmpty())
            items(albumList){
                AlbumCard(album = it, model)
            }
        }
    }
}

@Composable
fun SongCard(song: Song, model: FreezerModel) {

    Card(border = BorderStroke(2.dp, Color(152,35,136)),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 70.dp)
            .padding(5.dp)
            .clickable(onClick = {
                model.songCode = song.preview
                model.startPlayer()
                model.currentSongTitle = song.title
                model.currentSong = song
            })
        ){
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(3.dp)) {
                Text(text = song.title, fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(4.dp,4.dp,1.dp,1.dp))
                Text(text = song.artist["name"].toString(),
                modifier = Modifier.padding(4.dp,2.dp,1.dp,1.dp))
            }
            Column(horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
                    .padding(6.dp)){
                FavButton(model, song)
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album, model: FreezerModel){
    Card(border = BorderStroke(3.dp, Color(9,162,121)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = {
                model.currentTrackList = album.trackListLink
                model.currentAlbumTitle = album.title
                model.loadSongByAlbumAsync(model.currentTrackList)
            })){
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = album.title, fontSize = 18.sp, fontFamily = FontFamily.SansSerif)
                Text(text = album.artist["name"].toString())

            }
        }
    }
}

@Composable
fun PlayButton(model: FreezerModel) {
    with(model) {
        if (playerIsReady)
        IconButton(
            onClick = { startPlayer() },
            modifier = Modifier.background(
                SolidColor(Color(9,162,121)),
                shape = CircleShape
            )
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = "Play")
        }
        if (!playerIsReady)
            IconButton(
                onClick = { pausePlayer() },
                modifier = Modifier.background(
                    SolidColor(Color(9,162,121)),
                    shape = CircleShape
                )
            ) {
                Icon(Icons.Filled.Pause, contentDescription = "Pause")
            }
    }
}

@Composable
fun StartButton(model: FreezerModel){
    with(model){
            IconButton(
                onClick = { playFromStart() },
                modifier = Modifier.background(
                    SolidColor(Color(9,162,121)),
                    shape = CircleShape
                )
            ) {
                Icon(Icons.Filled.SkipPrevious, contentDescription = "Play")
            }

    }
}

@Composable
fun SkipButton(model: FreezerModel){
    with(model){
        IconButton(onClick = { playNextSong() },
            modifier = Modifier.background(
                SolidColor(Color(9,162,121)),
                shape = CircleShape))
        {
            Icon(Icons.Filled.SkipNext, contentDescription = "SkipNext")
        }
    }
}

@Composable
fun PlayBar(model: FreezerModel){
    with(model){
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Magenta),
        border = BorderStroke(3.dp, Color(8,80,60)))
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally)
                {
                Text(text = currentSongTitle)
                Row(horizontalArrangement = Arrangement.spacedBy(3.dp)){
                    StartButton(model)
                    PlayButton(model)
                    SkipButton(model)
                }
            }
        }
    }
}

@Composable
fun SearchChoseBar(model: FreezerModel){
    with(model){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = searchForSong,
                        onCheckedChange = {searchForSong = !searchForSong},
                        colors = CheckboxDefaults.colors(Color(152,35,136))
                )
                Text(text = "nach Song suchen")
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = searchForAlbum,
                        onCheckedChange = {searchForAlbum = !searchForAlbum},
                        colors = CheckboxDefaults.colors(Color(152,35,136)))
                Text(text = "nach Album suchen")
            }
        }
    }

}








