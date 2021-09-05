package com.example.tokarev.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GifDao {

    @Query("SELECT * FROM latest")
    suspend fun getAllFromLatest(): List<LatestEntity>

    @Insert
    suspend fun insertLatestGif(latestGif: LatestEntity)
}