package fhnw.emoba.freezerapp.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.model.FreezerModel



@Composable
fun RadioScreen(model: FreezerModel) {
            model.loadRadioHitsAsync()
    Box(contentAlignment = Alignment.Center,
        modifier         = Modifier.fillMaxSize())
    { Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        if(!FreezerModel.radioTrackList.isEmpty()){
             GoBackButton(model)
            }
        RadioList(model)
    }



        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)){
            PlayBar(model = model)
        }
    }

}

@Composable
fun RadioList(model: FreezerModel){
    with(model){

        LazyColumn{
            if (radioTrackList.isEmpty()){
                items(radioList){
                    RadioCard(radio = it, model)
                }}else if(!radioTrackList.isEmpty()){
                items(radioTrackList){
                    SongCard(song = it, model)
                }

            }
        }
    }

}

@Composable
fun GoBackButton(model: FreezerModel){
    IconButton(
        onClick = { model.radioTrackList = emptyList() },
        modifier = Modifier.padding(10.dp)
            .background(
            SolidColor(Color(35,78,184)),
            shape = CircleShape
        )
    ) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "GoBack", tint = Color.Black)
    }
}

@Composable
fun RadioCard(radio: Radio, model: FreezerModel){

    Card (
        backgroundColor = Color(35,78,184),
        border = BorderStroke(3.dp, Color(19,47,115)),
        modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable(onClick = {
            model.currentTrackList = radio.trackListe
            model.loadTrackListOfRadioAsync(model.currentTrackList)
        }))
    {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .fillMaxWidth()
        )
        {
            Text(text = radio.title,
                modifier = Modifier.align(Alignment.Center))
        }
    }

}



