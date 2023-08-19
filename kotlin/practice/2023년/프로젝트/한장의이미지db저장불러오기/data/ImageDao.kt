package com.example.testttt1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    fun getAll(): Flow<List<ImageEntity>>

    @Insert
    fun insertAll(vararg images: ImageEntity)
}