package com.jpmorgan.album.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jpmorgan.album.utils.DatabaseConstants.TABLE_ALBUM_DETAIL

object RoomMigration {

    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("Alter table $TABLE_ALBUM_DETAIL Add column userId INTEGER DEFAULT 1 NOT NULL")
        }

    }
}