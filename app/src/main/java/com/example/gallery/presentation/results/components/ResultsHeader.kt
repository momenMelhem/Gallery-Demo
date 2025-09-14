package com.example.gallery.presentation.results.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gallery.R

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
            text = stringResource(R.string.results),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Row {

            IconButton(
                onClick = onToggleView
            ) {
                Icon(
                    painterResource(R.drawable.list) ,
                    contentDescription = "Toggle View",
                    tint = if (isGridView) Color.Gray else Color.White
                )
            }

            IconButton(
                onClick = onToggleView
            ) {
                Icon(
                    painterResource(R.drawable.grid) ,
                    contentDescription = "Toggle View",
                    tint = if (isGridView) Color.White else Color.Gray
                )
            }


        }
    }
}