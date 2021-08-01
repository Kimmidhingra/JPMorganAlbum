package com.jpmorgan.album.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.jpmorgan.album.data.Album
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDaoTest : AlbumDatabaseTest() {
    private lateinit var albumDao: AlbumDaob

    @Before
    override fun setUp() {
        super.setUp()
        albumDao = albumDatabase.albumDao()
    }

    @Test
    fun addAlbumAndGetAlbums() {
        runBlocking {
            val album = Album(1, "My First Album", 101)
            albumDao.saveAlbum(album)
            val albums = albumDao.getAlbums()
            albums.first().contains(album)
            Truth.assertThat(albums.first().contains(album))
        }
    }

    @Test
    fun addAlbumsAndGetAlbums() {
        runBlocking {
            val list = listOf(Album(1, "My First Album", 101), Album(2, "My Second Album", 101))
            albumDao.saveAlbums(list)
            val albums = albumDao.getAlbums()
            Truth.assertThat(albums.first().size == list.size)
            Truth.assertThat(albums.first()[0] == list[0])
        }
    }
}