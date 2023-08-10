package com.example.numberbookpractice.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "phone") var phone: String? = null,
    @ColumnInfo(name = "email") var email: String? = null,
)
