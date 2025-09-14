package com.example.gallery.presentation.results

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.domain.usecase.GetImagesUseCase
import com.example.gallery.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val getImages: GetImagesUseCase
) : ViewModel() {

    init {
        fetchImages()
    }

    private val _state = mutableStateOf(ResultsState())
    val state = _state
    private fun fetchImages() {
        getImages().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = ResultsState(
                        photos = result.data ?: emptyList(),
                    )
                }

                is Resource.Error -> {
                    _state.value = ResultsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state?.value = ResultsState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}