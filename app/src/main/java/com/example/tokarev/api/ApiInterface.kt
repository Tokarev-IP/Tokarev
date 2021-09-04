package com.example.tokarev.api

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single
import retrofit2.http.Path

interface ApiInterface {

    @GET("/latest/{page_number}?json=true")
    fun getLatestGif(
        @Path("page_number") pageNumber: Int,
    ): Single<ApiData>

    @GET("latest")
    suspend fun getLatestGifCor(
        @Query("page_number") pageNumber: String,
    ): ApiData
}