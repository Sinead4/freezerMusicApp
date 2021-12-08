package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import org.json.JSONArray
import org.json.JSONObject

class Song(json: JSONObject){
        val title = json.getString("title_short")
        val artist = json.getJSONObject("artist")
        val preview = json.getString("preview")
        var isFavorite = false

    constructor(jsonString: String) : this(JSONObject(jsonString))

}


