package com.example.gallery.presentation.results

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gallery.navigation.Screens
import com.example.gallery.presentation.results.components.PhotosList
import com.example.gallery.presentation.results.components.ResultsHeader
import com.example.gallery.presentation.results.components.SearchSection
import com.google.gson.Gson

@Composable
fun ImageResults(
    viewModel: ResultsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    var isGridView by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF15202B))
    ) {
        SearchSection()

        ResultsHeader(
            isGridView = isGridView,
            onToggleView = {
                isGridView = !isGridView
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Blue
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
                            onClick = {
                            }
                        ) {
                            Text("Retry")
                        }
                    }
                }

                else -> {
                    PhotosList(
                        photos = state.photos,
                        isGridView = isGridView,
                        onPhotoClick = { photos ->

                            val json = Uri.encode(Gson().toJson(photos))
                            navController.navigate(Screens.Details.route + "/$json")
                        },
                    )
                }
            }
        }
    }
}

