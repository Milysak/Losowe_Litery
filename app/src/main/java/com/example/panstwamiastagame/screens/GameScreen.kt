package com.example.panstwamiastagame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.panstwamiastagame.dataclasses.Settings
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.panstwamiastagame.components.gamescreen.AlertDialogEndGame
import com.example.panstwamiastagame.components.gamescreen.ButtonsBottomBar
import com.example.panstwamiastagame.components.gamescreen.DrawnLettersGrid
import com.example.panstwamiastagame.components.gamescreen.RandomLetterLazyRow
import com.example.panstwamiastagame.components.gamescreen.TimeCountDownLinearIndicator
import com.example.panstwamiastagame.viewmodels.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    settings: Settings?,
    viewModel: GameViewModel = viewModel(),
    navController: NavController,
) {
    val mContext = LocalContext.current

    val lazyRowState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        if (settings != null) {
            viewModel.updateSettings(settings = settings)
        }
        lazyRowState.scrollToItem(3)
    }

    if(lazyRowState.isScrollInProgress) {
        DisposableEffect(Unit) {
            onDispose {
                if (!viewModel.startTimerDown) {
                    coroutineScope.launch {
                        val layoutInfo = lazyRowState.layoutInfo
                        val visibleItemsInfo = layoutInfo.visibleItemsInfo

                        visibleItemsInfo.forEach {
                            val delta = it.size / 2
                            val center = lazyRowState.layoutInfo.viewportEndOffset / 2
                            val childCenter = it.offset + it.size / 2
                            val target = childCenter - center
                            val r = if (target in -delta..delta) target else 0
                            lazyRowState.animateScrollBy(r.toFloat())

                            val index = if (target in -delta..delta) it.index else null
                            if (index != null) {
                                viewModel.listOfLetters[index%viewModel.listOfLetters.size].isDrawn = true
                                viewModel.listOfDrawnLetters[viewModel.listOfDrawnLetters.indexOf(null)] = viewModel.listOfLetters[index%viewModel.listOfLetters.size]
                            }
                        }

                        viewModel.startTimerDown = true
                    }
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            ButtonsBottomBar(
                viewModel = viewModel,
                navController = navController,
                coroutineScope = coroutineScope,
                lazyRowState = lazyRowState
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    paddingValues = innerPadding
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 32.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            RandomLetterLazyRow(
                viewModel = viewModel,
                lazyRowState = lazyRowState
            )

            DrawnLettersGrid(
                viewModel = viewModel
            )
        }

        TimeCountDownLinearIndicator(
            viewModel = viewModel
        )

        AlertDialogEndGame(
            endGame = viewModel.endGame,
            navController = navController
        )
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(
        navController = rememberNavController(),
        settings = null
    )
}