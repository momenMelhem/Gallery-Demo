package com.example.gallery.presentation.results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.gallery.domain.model.Photos

@Composable
fun PhotoItem(
    photo: Photos,
    isGridView: Boolean,
    onClick: () -> Unit,
) {

    var isFavorite by rememberSaveable { mutableStateOf(photo.isFavorite) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF15202B)
        )
    ) {
        Column {
            Box {
                AsyncImage(
                    model = photo.url,
                    contentDescription = "${photo.description}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isGridView) 150.dp else 200.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(if (isGridView) Alignment.TopStart else Alignment.TopEnd)

                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .background(
                                color = if (isFavorite) Color.White else Color(
                                    255,
                                    255,
                                    255,
                                    29
                                )
                            )
                    ) {
                        IconButton(
                            onClick = {
                                isFavorite = isFavorite.not()
                            },
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) Color.Red else Color.White
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = photo.description ?: photo.altDescription,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = photo.description ?: photo.altDescription,
                    fontSize = 16.sp,
                    color = Color(0xFFAAB8C2),
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}