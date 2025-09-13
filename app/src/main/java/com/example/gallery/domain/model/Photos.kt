package com.example.gallery.domain.model

import com.example.gallery.data.remote.dto.Links
import com.example.gallery.data.remote.dto.Urls

data class Photos(
    val altDescription: String,
    val description: String?,
    val links: Links,
    val slug: String,
    val urls: Urls,
)

