package com.example.panstwamiastagame.components.gamescreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panstwamiastagame.viewmodels.GameViewModel

@Composable
fun DrawnLettersGrid(
    viewModel: GameViewModel
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .padding(48.dp),
        columns = StaggeredGridCells.Fixed(5),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.listOfDrawnLetters.size) { index ->
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if(viewModel.listOfDrawnLetters[index] == null)
                    Color.Transparent
                else
                    viewModel.listOfDrawnLetters[index]?.color!!,
                border = if(viewModel.listOfDrawnLetters[index] == null)
                    BorderStroke(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.onBackground.copy(0.75f),
                                Color(0xffC8CFA0).copy(alpha = 0.75f)
                            )
                        )
                    )
                else
                    null
            ) {
                Text(
                    modifier = Modifier
                        .animateContentSize()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 12.dp
                        ),
                    text = if(viewModel.listOfDrawnLetters[index] == null)
                        (index + 1).toString()
                    else
                        viewModel.listOfDrawnLetters[index]?.letter!!,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = if(viewModel.listOfDrawnLetters[index] == null)
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
                    else
                        Color.White
                )
            }
        }
    }
}