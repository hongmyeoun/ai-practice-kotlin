//AppDatabase class
package com.example.testttt.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//entities = [원하는것::class]
@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE User ADD COLUMN phone TEXT")
        database.execSQL("ALTER TABLE User ADD COLUMN email TEXT")
        database.execSQL("ALTER TABLE User ADD COLUMN age INTEGER")
    }
}
