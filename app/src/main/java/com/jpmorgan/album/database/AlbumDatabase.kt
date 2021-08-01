package com.jpmorgan.album.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpmorgan.album.data.Album

@Database(entities = arrayOf(Album::class), version = 2)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}