package com.example.tokarev.api

import com.google.gson.annotations.SerializedName

data class ResultData(

    @SerializedName("id")
    var id: Int,

    @SerializedName("description")
    var description: String,

    @SerializedName("votes")
    var votes: Int,

    @SerializedName("author")
    var author: String,

    @SerializedName("date")
    var date: String,

    @SerializedName("gifURL")
    var gif_url: String,

    @SerializedName("gifSize")
    var gifSize: String,

    @SerializedName("previewURL")
    var preview_url: String,

    @SerializedName("videoURL")
    var video_url: String,

    @SerializedName("videoPath")
    var video_path: String,

    @SerializedName("videoSize")
    var video_size: Int,

    @SerializedName("type")
    var type: String,

    @SerializedName("width")
    var width: Int,

    @SerializedName("height")
    var height: Int,

    @SerializedName("commentCount")
    var comment_count: Int,

    @SerializedName("fileSize")
    var file_size: Int,

    @SerializedName("canVote")
    var can_vote: Boolean,

    )
