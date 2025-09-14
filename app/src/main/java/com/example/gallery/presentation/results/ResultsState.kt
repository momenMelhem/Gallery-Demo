package com.example.gallery.presentation.results

import com.example.gallery.domain.model.Photos

data class ResultsState(
    val isLoading : Boolean = false,
    val photos : List<Photos> = emptyList(),
    val error : String = "",
    val isFavorite: Boolean = false
    )
