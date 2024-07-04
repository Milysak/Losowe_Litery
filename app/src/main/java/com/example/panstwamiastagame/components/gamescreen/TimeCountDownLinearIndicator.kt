package com.example.panstwamiastagame.components.gamescreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.panstwamiastagame.viewmodels.GameViewModel

@Composable
fun TimeCountDownLinearIndicator(
    viewModel: GameViewModel
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (viewModel.startTimerDown) 0f else 1f,
        animationSpec = tween(
            durationMillis = if (viewModel.startTimerDown) viewModel.timeOfRound else 0,
            easing = LinearEasing
        ),
        label = "progress",
        finishedListener = {
            if (it == 0f) {
                if(viewModel.listOfDrawnLetters.last() != null) {
                    viewModel.endGame = true
                } else {
                    viewModel.randomLetter = false
                    viewModel.startTimerDown = false
                }
            }
        }
    )

    Box {
        LinearProgressIndicator(
            progress = {
                animatedProgress
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            trackColor = Color.Transparent,
            color = Color(0xffEF9C66)
        )
    }
}