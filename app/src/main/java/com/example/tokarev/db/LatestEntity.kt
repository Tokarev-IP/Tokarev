package com.example.tokarev.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest")
data class LatestEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "gifUrl")
    val gifUrl: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "number")
    val number: Int,
)
