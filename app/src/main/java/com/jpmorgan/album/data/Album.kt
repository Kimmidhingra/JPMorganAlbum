package com.jpmorgan.album.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jpmorgan.album.utils.DatabaseConstants.TABLE_ALBUM_DETAIL

@Entity(tableName = TABLE_ALBUM_DETAIL)
data class Album(@PrimaryKey val id: Int, val title: String)