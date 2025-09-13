package com.example.gallery.data.remote

import com.example.gallery.data.remote.dto.PhotosResponse
import com.example.gallery.util.Constants.CLIENT_ID
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<PhotosResponse>
}