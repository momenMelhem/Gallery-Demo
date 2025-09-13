package com.example.gallery.data.repository

import com.example.gallery.data.remote.UnsplashApi
import com.example.gallery.data.remote.dto.PhotosResponse
import com.example.gallery.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl (
    private val api: UnsplashApi
) : PhotosRepository  {

    override suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotosResponse> {
        return api.getPhotos(page = page,perPage = perPage)
    }

}