package com.jpmorgan.album.repository

import androidx.room.withTransaction
import com.jpmorgan.album.database.AlbumDatabase
import com.jpmorgan.album.network.AlbumApi
import com.jpmorgan.album.utils.networkBoundResource
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumApi: AlbumApi,
    private val albumDatabase: AlbumDatabase
) {

    private val albumDao = albumDatabase.albumDao()
    fun getAlbums() = networkBoundResource(
        query = {
            albumDao.getAlbums()
        },
        fetch = {
            albumApi.getAlbums()
        },
        saveFetchResult = { albums ->
            albumDatabase.withTransaction {
                albumDao.saveAlbums(albums)
            }
        }
    )


}
