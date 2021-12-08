package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.data.Song
import fhnw.emoba.freezerapp.data.SongService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object FreezerModel {

    var selectedTab by mutableStateOf(AvailableTabs.HOME)
    var service = SongService()

    var playerIsReady by mutableStateOf(true)

    var songCode by mutableStateOf("")
    var searchInput by mutableStateOf("")

    var songLists: List<Song> by mutableStateOf(emptyList())
    var albumList: List<Album> by mutableStateOf(emptyList())
    var favoriteList = mutableListOf<Song>()
    var radioList: List<Radio> by mutableStateOf(emptyList())
    var radioTrackList: List<Song> by mutableStateOf(emptyList())

    var isLoading by mutableStateOf(false)
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)


    var searchForSong by mutableStateOf(false)
    var searchForAlbum by mutableStateOf(false)

    var currentSongTitle by mutableStateOf("")
    var currentAlbumTitle by mutableStateOf("")
    var currentlyPlaying = ""
    var currentTrackList by mutableStateOf("")
    var currentSong: Song? by mutableStateOf(null)


    private val player = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        setOnPreparedListener {
            start()
        }
        setOnCompletionListener { playerIsReady = true }
    }

    fun startPlayer(){
        playerIsReady = false

        try {
            if (currentlyPlaying == songCode && !player.isPlaying) {
                player.start()
            } else {
                currentlyPlaying = songCode
                player.reset()
                player.setDataSource(songCode)
                player.prepareAsync()
            }
        } catch (e: Exception) {
            playerIsReady = true
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }

    fun playFromStart(){
        player.seekTo(0)
        player.start()
        playerIsReady = false

    }

    fun playNextSong(){
        playerIsReady = true
        val currentSongPos = getcurrentSongPos(currentSong)
        val nextSong = getNextSong(currentSongPos)


        if(nextSong != null){
            songCode = nextSong.preview
            currentSongTitle = nextSong.title
            currentSong = nextSong
            startPlayer()
            playerIsReady = false

        }


    }

    fun getcurrentSongPos(song: Song?): Int{
        if (songLists.isNotEmpty()){
            return songLists.indexOf(song)
        }
        else if(radioTrackList.isNotEmpty()){
            return radioTrackList.indexOf(song)
        }
        else if(favoriteList.isNotEmpty()){
            return favoriteList.indexOf(song)
        }
        else{
            return 0
        }

    }

    fun getNextSong(pos: Int): Song?{
        if (songLists.isNotEmpty() && pos < songLists.size){
            return songLists[pos + 1]

        }  else if(radioTrackList.isNotEmpty() && pos < radioTrackList.size){
            return radioTrackList[pos + 1]

            } else if(favoriteList.isNotEmpty() && pos < favoriteList.size){
                return favoriteList[pos + 1]

                }else{
                return null
        }

    }


    fun loadSongByNameAsync(searchParam: String){
        isLoading = true

        modelScope.launch {
            if (searchForSong) {
                songLists = emptyList()
                albumList = emptyList()
                songLists = service.loadSongs(searchParam).distinct()
            }else if(searchForAlbum){
                albumList = emptyList()
                songLists = emptyList()
                albumList = service.loadAlbums(searchParam).distinct()
            }
            isLoading = false
        }

    }

    fun loadSongByAlbumAsync(trackListLink: String){
        isLoading = true

        modelScope.launch {
                songLists = emptyList()
                albumList = emptyList()
                songLists = service.loadTrackList(trackListLink)

                isLoading = false
        }

    }

    fun loadTrackListOfRadioAsync(trackListLink: String){
        isLoading = true

        modelScope.launch {
             radioTrackList = emptyList()
             songLists = emptyList()
             radioTrackList = service.loadTrackList(trackListLink)

             isLoading = false
        }

    }


    fun addToFavorites(song: Song){
        if (!favoriteList.contains(song)){
            favoriteList.add(song)
            song.isFavorite= true
        } else if(favoriteList.contains(song)){
            favoriteList.remove(song)
            song.isFavorite = false
        }
    }

    fun loadRadioHitsAsync(){
        isLoading = true

        modelScope.launch {
            radioList = emptyList()
            radioList = service.loadRadios()

            isLoading = false
        }
    }


}