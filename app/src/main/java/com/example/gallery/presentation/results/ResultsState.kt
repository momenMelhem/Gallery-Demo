package com.example.gallery.presentation.results

import com.example.gallery.domain.model.Photos

data class ResultsState(
    val isLoading : Boolean = false,
    val photos : List<Photos> = emptyList<Photos>(),
    val error : String = ""
    )
