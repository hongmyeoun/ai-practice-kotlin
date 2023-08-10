package com.example.numberbookpractice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class NumberBookAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: NumberBookAppDatabase? = null

        fun getDatabase(context: Context): NumberBookAppDatabase {
            //synchronized == 스레드 같은거 -> 동시생성을 방지하기 위해 사용하는것
            //1. 새로 생성한 사람들
            return INSTANCE?: synchronized(this){
                val db = Room.databaseBuilder(
                    context,
                    NumberBookAppDatabase::class.java, "my.db"
                ).build()
                db
            }
        }
    }
}