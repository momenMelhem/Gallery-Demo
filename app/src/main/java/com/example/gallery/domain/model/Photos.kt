package com.example.gallery.domain.model


data class Photos(
            val altDescription: String,
            val description: String?,
            val url: String ,
            var isFavorite : Boolean = false
        )
