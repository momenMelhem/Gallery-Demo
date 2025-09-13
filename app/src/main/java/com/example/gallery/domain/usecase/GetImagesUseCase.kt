package com.example.gallery.domain.usecase

import com.example.gallery.data.remote.dto.toPhotos
import com.example.gallery.domain.model.Photos
import com.example.gallery.domain.repository.PhotosRepository
import com.example.gallery.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: PhotosRepository
) {
    operator fun invoke(): Flow<Resource<List<Photos>>> = flow {
        try {
            emit(Resource.Loading())
            val photos = repository.getPhotos().map { it.toPhotos() }
            emit(Resource.Success(photos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}