package com.jpmorgan.album.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpmorgan.album.data.Album
import com.jpmorgan.album.utils.DatabaseConstants.TABLE_ALBUM_DETAIL
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbums(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(albums: Album)

    @Query("Select * from $TABLE_ALBUM_DETAIL")
    fun getAlbums() : Flow<List<Album>>
}