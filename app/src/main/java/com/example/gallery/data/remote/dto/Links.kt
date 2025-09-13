package com.example.gallery.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Links(
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String,
    val html: String,
    val self: String
)