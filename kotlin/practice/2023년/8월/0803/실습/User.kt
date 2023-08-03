//User data class
package com.example.testttt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("phone") val phone: String? = null,
    @ColumnInfo("email") val email: String? = null,
    @ColumnInfo("age") val age: Int? = null,
)
