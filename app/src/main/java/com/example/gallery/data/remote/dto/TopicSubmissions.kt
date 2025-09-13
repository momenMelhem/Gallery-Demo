package com.example.gallery.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("people")
    val people: People,
    @SerializedName("film")
    val film : Film
)
data class People(
    val status: String,
    @SerializedName("approved_on")
    val approvedOn: String
)
data class Film(
    val status: String,
    @SerializedName("approved_on")
    val approvedOn: String
)