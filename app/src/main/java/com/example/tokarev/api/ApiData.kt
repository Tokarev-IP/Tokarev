package com.example.tokarev.api

import com.google.gson.annotations.SerializedName

data class ApiData(

    @SerializedName("result")
    var result: List<ResultData>,

    @SerializedName("totalCount")
    var total_count: Int,

)
