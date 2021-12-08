package fhnw.emoba.freezerapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.freezerapp.data.Song
import fhnw.emoba.freezerapp.model.FreezerModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 *  ACHTUNG: Auf Android arbeiten wir (noch) mit JUNIT 4
 */
@RunWith(AndroidJUnit4::class)
class FreezerModelTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("fhnw.freezerapp", appContext.packageName)
    }

    @Test
    fun testloadSongs(){
        //given
        val model = FreezerModel

        //when
        model.searchForSong = true
        model.searchForAlbum = false
        model.loadSongByNameAsync("Hallo")

        //then
        assertTrue(model.albumList.isEmpty())
        assertNotNull(model.songLists)
    }

    @Test
    fun testloadAlbums(){
        //given
        val model = FreezerModel

        //when
        model.searchForAlbum = true
        model.searchForSong = false
        model.loadSongByAlbumAsync("Hallo")

        //then
        assertTrue(model.songLists.isEmpty())
        assertNotNull(model.albumList)
    }

    @Test
    fun testAddToFavorites(){
        //given
        val model = FreezerModel
        val jsonString = "{\n" +
                "      \"id\": \"1109731\",\n" +
                "      \"readable\": true,\n" +
                "      \"title\": \"Lose Yourself (From \\\"8 Mile\\\" Soundtrack)\",\n" +
                "      \"title_short\": \"Lose Yourself\",\n" +
                "      \"title_version\": \"(From \\\"8 Mile\\\" Soundtrack)\",\n" +
                "      \"link\": \"https://www.deezer.com/track/1109731\",\n" +
                "      \"duration\": \"326\",\n" +
                "      \"rank\": \"985755\",\n" +
                "      \"explicit_lyrics\": true,\n" +
                "      \"explicit_content_lyrics\": 1,\n" +
                "      \"explicit_content_cover\": 0,\n" +
                "      \"preview\": \"https://cdns-preview-1.dzcdn.net/stream/c-13039fed16a173733f227b0bec631034-12.mp3\",\n" +
                "      \"md5_image\": \"e2b36a9fda865cb2e9ed1476b6291a7d\",\n" +
                "      \"artist\": {\n" +
                "        \"id\": \"13\",\n" +
                "        \"name\": \"Eminem\",\n" +
                "        \"link\": \"https://www.deezer.com/artist/13\",\n" +
                "        \"picture\": \"https://api.deezer.com/artist/13/image\",\n" +
                "        \"picture_small\": \"https://e-cdns-images.dzcdn.net/images/artist/19cc38f9d69b352f718782e7a22f9c32/56x56-000000-80-0-0.jpg\",\n" +
                "        \"picture_medium\": \"https://e-cdns-images.dzcdn.net/images/artist/19cc38f9d69b352f718782e7a22f9c32/250x250-000000-80-0-0.jpg\",\n" +
                "        \"picture_big\": \"https://e-cdns-images.dzcdn.net/images/artist/19cc38f9d69b352f718782e7a22f9c32/500x500-000000-80-0-0.jpg\",\n" +
                "        \"picture_xl\": \"https://e-cdns-images.dzcdn.net/images/artist/19cc38f9d69b352f718782e7a22f9c32/1000x1000-000000-80-0-0.jpg\",\n" +
                "        \"tracklist\": \"https://api.deezer.com/artist/13/top?limit=50\",\n" +
                "        \"type\": \"artist\"\n" +
                "      },\n" +
                "      \"album\": {\n" +
                "        \"id\": \"119606\",\n" +
                "        \"title\": \"Curtain Call: The Hits\",\n" +
                "        \"cover\": \"https://api.deezer.com/album/119606/image\",\n" +
                "        \"cover_small\": \"https://e-cdns-images.dzcdn.net/images/cover/e2b36a9fda865cb2e9ed1476b6291a7d/56x56-000000-80-0-0.jpg\",\n" +
                "        \"cover_medium\": \"https://e-cdns-images.dzcdn.net/images/cover/e2b36a9fda865cb2e9ed1476b6291a7d/250x250-000000-80-0-0.jpg\",\n" +
                "        \"cover_big\": \"https://e-cdns-images.dzcdn.net/images/cover/e2b36a9fda865cb2e9ed1476b6291a7d/500x500-000000-80-0-0.jpg\",\n" +
                "        \"cover_xl\": \"https://e-cdns-images.dzcdn.net/images/cover/e2b36a9fda865cb2e9ed1476b6291a7d/1000x1000-000000-80-0-0.jpg\",\n" +
                "        \"md5_image\": \"e2b36a9fda865cb2e9ed1476b6291a7d\",\n" +
                "        \"tracklist\": \"https://api.deezer.com/album/119606/tracks\",\n" +
                "        \"type\": \"album\"\n" +
                "      },\n" +
                "      \"type\": \"track\"\n" +
                "    }"

        val song = Song(jsonString)

        //when
        model.addToFavorites(song)

        //then
        assertTrue(model.favoriteList.contains(song))
        assertTrue(song.isFavorite)
    }
}