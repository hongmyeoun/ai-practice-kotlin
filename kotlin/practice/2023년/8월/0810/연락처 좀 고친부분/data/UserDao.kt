package com.example.numberbookpractice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun getUserByUid(uid: Int): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun updateUser(user: User)

}