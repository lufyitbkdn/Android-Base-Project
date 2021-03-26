package com.shapee.sample.data.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.data.contact.local.ContactDao

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        const val DB_NAME = "AppDatabase"

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}