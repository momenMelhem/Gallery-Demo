package com.example.gallery.di

import com.example.gallery.data.remote.UnsplashApi
import com.example.gallery.data.repository.PhotosRepositoryImpl
import com.example.gallery.domain.repository.PhotosRepository
import com.example.gallery.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhotosRepository(api: UnsplashApi) : PhotosRepository {
        return PhotosRepositoryImpl(api)
    }
}