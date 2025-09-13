package com.example.gallery.presentation.results

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.gallery.domain.model.Photos

@Composable
fun ImageResults(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var isGridView by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2328))
    ) {
        SearchSection(
        )

        // Results Header
        ResultsHeader(
            isGridView = isGridView,
            onToggleView = {
                isGridView = !isGridView
            }
        )

        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                state.error.isNotBlank() -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.error,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.onEvent(ResultsEvent.Refresh) }
                        ) {
                            Text("Retry")
                        }
                    }
                }
                else -> {
                    PhotosList(
                        cars = state.photos,
                        isGridView = state.isGridView,
                        onCarClick = onNavigateToDetail,
                        onFavoriteClick = { carId ->
                            viewModel.onEvent(ResultsEvent.ToggleFavorite(carId))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchSection(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Search",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Search your image...",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = Color(0xFF2A2F35),
//                unfocusedContainerColor = Color(0xFF2A2F35),
//                textColor = Color.White,
//                backgroundColor = Color(0xFF2A2F35),
//                focusedBorderColor = Color.White,
//                unfocusedBorderColor = Color.Gray,
//                cursorColor = Color.White,
//                placeholderColor = Color.Gray,
//                focusedPlaceholderColor = Color.Gray,
//
//            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun ResultsHeader(
    isGridView: Boolean,
    onToggleView: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Results",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Row {
            IconButton(
                onClick = onToggleView
            ) {
//                Icon(
//                    if (isGridView) Icons.Default.List else Icons.Default.GridView,
//                    contentDescription = "Toggle View",
//                    tint = Color.White
//                )
            }
        }
    }
}
@Composable
fun PhotosList(
    photos: List<Photos>,
    isGridView: Boolean,
    onCarClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isGridView) 2 else 1),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(
                photo = photo,
                isGridView = isGridView,
                onClick = { onCarClick(photo) },
                onFavoriteClick = { onFavoriteClick(photo) }
            )
        }
    }
}
@Composable
fun PhotoItem(
    photo: Photos,
    isGridView: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2F35)
        )
    ) {
        Column {
            Box {
                AsyncImage(
                    model = car.imageUrl,
                    contentDescription = "${car.brand} ${car.model}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isGridView) 150.dp else 200.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        if (car.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (car.isFavorite) Color.Red else Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = car.brand,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = car.model,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = if (isGridView) 2 else 3,
                    overflow = TextOverflow.Ellipsis
                )

                if (!isGridView) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = car.description,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}