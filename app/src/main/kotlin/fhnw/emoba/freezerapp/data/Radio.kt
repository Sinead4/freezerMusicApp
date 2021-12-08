package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Radio(json: JSONObject){
    val title = json.getString("title")
    val trackListe = json.getString("tracklist")


    constructor(jsonString: String) : this(JSONObject(jsonString))

}