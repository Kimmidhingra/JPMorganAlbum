package com.jpmorgan.album.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpmorgan.album.data.Album
import com.jpmorgan.album.data.AlbumDao

@Database(entities = arrayOf(Album::class), version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}