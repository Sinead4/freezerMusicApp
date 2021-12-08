package fhnw.emoba.freezerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.AppUI


object FreezerApp : EmobaApp {

    private lateinit var model : FreezerModel

    override fun initialize(activity: ComponentActivity) {
        model = FreezerModel
    }

    @ExperimentalComposeUiApi
    @Composable
    override fun CreateUI() {
        AppUI(FreezerModel)
    }

}

