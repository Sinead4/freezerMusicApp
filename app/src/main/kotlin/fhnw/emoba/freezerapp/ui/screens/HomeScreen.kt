package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.model.FreezerModel


    @Composable
    fun HomeScreen(model: FreezerModel) {
        Box(contentAlignment = Alignment.Center,
            modifier         = Modifier.fillMaxSize()
        ){
            Column() {
                Text(text  = "hello!",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 48.sp)
                )
                Text(text  = "welcome to",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 48.sp)
                )
                Image(painter = painterResource(id = R.drawable.deezerlogo_v2), modifier = Modifier.requiredSize(300.dp),
                      contentDescription = "Deezer Logo")
            }

        }
    }
