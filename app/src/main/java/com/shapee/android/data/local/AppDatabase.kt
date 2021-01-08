package com.shapee.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shapee.android.model.Child

@Database(entities = [Child::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun childrenDao(): ChildrenDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Children ADD COLUMN phone TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}