package com.example.tokarev.api

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single
import retrofit2.http.Path

interface ApiInterface {

    @GET("/latest/{page_number}?json=true")
    suspend fun getLatestGif(
        @Path("page_number") pageNumber: Int,
    ): ApiData
}