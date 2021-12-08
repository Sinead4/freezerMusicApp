package fhnw.emoba.freezerapp.model

import androidx.compose.ui.graphics.Color

enum class AvailableTabs (val title: String, val color: Color){
    HOME("home", Color(243,151,0)),
    SEARCH("search", Color(152,35,136)),
    RADIO("radio", Color(35,78,184)),
    FAVOURITES("favourites", Color(9,162,121))

}