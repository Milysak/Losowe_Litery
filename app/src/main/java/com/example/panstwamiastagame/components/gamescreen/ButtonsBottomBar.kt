package com.example.panstwamiastagame.components.gamescreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.panstwamiastagame.viewmodels.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ButtonsBottomBar(
    viewModel: GameViewModel,
    navController: NavController,
    coroutineScope: CoroutineScope,
    lazyRowState: LazyListState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.randomLetter = true
                    lazyRowState.animateScrollBy(
                        value = Random.nextInt(3000, 5000).toFloat(),
                        animationSpec = tween(4000)
                    )
                }
            },
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Color(0xff78ABA8),
                contentColor = Color.White
            ),
            enabled = !viewModel.randomLetter
        ) {
            Text(
                text = "LOSUJ"
            )
        }

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.outlinedButtonColors().copy(
                contentColor = Color(0xffEF9C66),
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = if(!viewModel.randomLetter) 1.dp else 0.dp,
                brush = Brush.linearGradient(
                    colors = arrayListOf(Color(0xff78ABA8), Color(0xffEF9C66))
                )
            ),
            enabled = !viewModel.randomLetter
        ) {
            Text(
                text = "ZAKOŃCZ",
            )
        }
    }
}