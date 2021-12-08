package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Album(json: JSONObject)
{
    val title = json.getString("title")
    val trackListLink = json.getString("tracklist")
    val artist = json.getJSONObject("artist")


}
