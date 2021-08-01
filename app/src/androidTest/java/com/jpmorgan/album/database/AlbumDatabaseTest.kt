package com.jpmorgan.album.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class AlbumDatabaseTest {

    private lateinit var _albumDatabase: AlbumDatabase
    val albumDatabase: AlbumDatabase
        get() = _albumDatabase


    @Before
    open fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        _albumDatabase = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        _albumDatabase.close()
    }

}