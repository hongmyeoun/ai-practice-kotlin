package com.example.phonenumberbook.data

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

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM User WHERE uid = :uid")
    fun getEntityByUid(uid: Int): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

}