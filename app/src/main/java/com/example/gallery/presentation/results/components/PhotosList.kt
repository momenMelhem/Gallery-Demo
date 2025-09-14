package com.example.gallery.presentation.results.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.gallery.domain.model.Photos

@Composable
fun PhotosList(
    photos: List<Photos>,
    isGridView: Boolean,
    onPhotoClick: (Photos) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isGridView) 2 else 1),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(photos, key = { it.description ?: it.altDescription }) { photo ->


            PhotoItem(
                photo = photo,
                isGridView = isGridView,
                onClick = { onPhotoClick(photo) },
            )
        }
    }
}