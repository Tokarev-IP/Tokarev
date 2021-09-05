package com.example.tokarev.mvvm

import com.example.tokarev.MyApplication
import com.example.tokarev.api.ApiData
import com.example.tokarev.api.ApiGif
import com.example.tokarev.db.GifDataBase
import com.example.tokarev.db.LatestEntity

class GifRepository {

    private val api = ApiGif.apiClient
    private val db = GifDataBase.getDatabase(MyApplication.applicationContext()).gifDao()

    suspend fun getLatestData(number: Int): ApiData {
        return api.getLatestGif(number)
    }

    suspend fun getAllFromRoom(): List<LatestEntity> {
        return db.getAllFromLatest()
    }


}