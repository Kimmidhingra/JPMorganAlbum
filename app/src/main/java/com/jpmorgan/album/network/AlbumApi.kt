package com.jpmorgan.album.network

import com.jpmorgan.album.data.Album
import retrofit2.http.GET

interface AlbumApi {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

}