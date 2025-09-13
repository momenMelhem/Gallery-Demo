package com.example.gallery.domain.repository

import com.example.gallery.data.remote.dto.PhotosResponse
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun getPhotos(page: Int = 0, perPage: Int = 10): List<PhotosResponse>

}