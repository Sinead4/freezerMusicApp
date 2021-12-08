package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.freezerapp.model.FreezerModel
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Song
import fhnw.emoba.freezerapp.model.AvailableTabs
import fhnw.emoba.freezerapp.ui.screens.*


@ExperimentalComposeUiApi
@Composable
fun AppUI(model : FreezerModel){

       AppTheme() {
//           Modifier.background(Color.Black)
           Scaffold (
//               Modifier.background(Color.Black),
                content = {Body(model)}
           )
    }
}

@ExperimentalComposeUiApi
@Composable
fun Body(model: FreezerModel) {
    with(model) {
            Modifier.background(Color.Black)
        MaterialTheme {
//                Modifier.background(Color.Black)
            Column() {
                TabRow(selectedTabIndex = selectedTab.ordinal) {
                    for (tab in AvailableTabs.values()) {
                        Tab(text     = { Text(tab.title) },
                            selected = tab == selectedTab,
                            onClick  = { selectedTab = tab},
                            modifier = Modifier.background(tab.color),
                            selectedContentColor = Color.Black)
                    }
                }

                Crossfade(targetState = selectedTab) { screen ->
                    when (screen) {
                        AvailableTabs.HOME -> {
                            HomeScreen(model = model)
                        }
                        AvailableTabs.SEARCH -> {
                            SearchScreen(model = model)
                        }
                        AvailableTabs.RADIO -> {
                            RadioScreen(model = model)
                        }
                        AvailableTabs.FAVOURITES -> {
                            FavouritesScreen(model = model)
                        }
                    }
                }
            }

        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun Preview(){
    AppUI(FreezerModel)
}
