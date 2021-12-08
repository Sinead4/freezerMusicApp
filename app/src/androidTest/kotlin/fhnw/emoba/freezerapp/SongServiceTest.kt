package fhnw.emoba.freezerapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.freezerapp.data.SongService
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongServiceTest {

    @Test
    fun testLoadRadios(){
        //given
        val service = SongService()

        //when
        val radioList = service.loadRadios()


        //then
        assertTrue(radioList.isNotEmpty())
        assertNotNull(radioList)
        assertEquals("Hits", radioList[0].title)
        assertTrue(radioList[0].trackListe.isNotEmpty())

    }


}