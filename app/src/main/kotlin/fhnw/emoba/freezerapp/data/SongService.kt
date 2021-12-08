package fhnw.emoba.freezerapp.data

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class SongService {
    val baseURL = "https://api.deezer.com"


    fun loadSongs(searchParam: String): List<Song> {
        var songList = mutableListOf<Song>()
        try {

            val url = "$baseURL/search/track?q=$searchParam"
            val jsonString = getJsonString(url)

            val obj = JSONObject(jsonString)
            val data =obj.getJSONArray("data")

            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                val songs = Song(item)
                songList.add(songs)
            }


            return songList


        } catch (e: Exception) {
            throw IllegalArgumentException("Something went wrong in loadSongs()")
        }
    }


    fun loadAlbums(albumName: String): List<Album> {
        var albumList = mutableListOf<Album>()

        try {

            val url = "$baseURL/search/album?q=$albumName"
            val jsonString = getJsonString(url)

            val obj = JSONObject(jsonString)
            val data = obj.getJSONArray("data")


            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                val album = Album(item)
                albumList.add(album)
            }

            return albumList


        } catch (e: Exception) {
            throw IllegalArgumentException("Something went wrong in loadAlbums()")
        }

    }

    fun loadTrackList(trackListLink: String) : List<Song>{
        var songList = mutableListOf<Song>()
        try {
            val url = trackListLink

            val jsonString = getJsonString(url)

            val obj = JSONObject(jsonString)
            val data =obj.getJSONArray("data")

            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                val songs = Song(item)
                songList.add(songs)
            }


            return songList


        } catch (e: Exception) {
            throw IllegalArgumentException("Something went wrong in loadSongs()")
        }
    }


    fun loadRadios(): List<Radio>{
        var radioList = mutableListOf<Radio>()

        try {
            val url = "$baseURL/radio"
            val jsonString = getJsonString(url)

            val obj = JSONObject(jsonString)
            val data = obj.getJSONArray("data")

            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                val radio = Radio(item)
                radioList.add(radio)
            }

            return radioList

        }catch (e: Exception){
            throw java.lang.IllegalArgumentException("Something went wrong in loadRadios e: " +e)
        }


    }

    fun getJsonString(url: String): String{
        val conn = URL(url).openConnection() as HttpsURLConnection
        conn.connect()

        val reader = BufferedReader(InputStreamReader(conn.inputStream, StandardCharsets.UTF_8))

        val jsonString = reader.readText()
        reader.close()

        return jsonString
    }
}