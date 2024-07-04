package com.example.panstwamiastagame.components.gamescreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panstwamiastagame.viewmodels.GameViewModel

@Composable
fun RandomLetterLazyRow(
    viewModel: GameViewModel,
    lazyRowState: LazyListState
) {
    fun Modifier.fadingEdge(brush: Brush) = this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }

    val leftRightFade = Brush.horizontalGradient(0f to Color.Transparent, 0.25f to Color.Red, 0.75f to Color.Red, 1f to Color.Transparent)

    LazyRow(
        modifier = Modifier
            .height(100.dp)
            .fadingEdge(
                leftRightFade
            ),
        state = lazyRowState,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = false
    ) {
        items(
            count = Int.MAX_VALUE,
            key = { it }
        ) { index ->
            RandomLetterLazyRowItem(
                viewModel = viewModel,
                index = index,
                lazyRowState = lazyRowState
            )
        }
    }
}

@Composable
fun RandomLetterLazyRowItem(
    viewModel: GameViewModel,
    index: Int,
    lazyRowState: LazyListState
) {
    if (!viewModel.listOfLetters[index % viewModel.listOfLetters.size].isDrawn) {
        val selected by remember {
            derivedStateOf {
                val layoutInfo = lazyRowState.layoutInfo
                val visibleItemsInfo = layoutInfo.visibleItemsInfo
                val itemInfo = visibleItemsInfo.firstOrNull { it.index == index }

                itemInfo?.let {
                    val delta = it.size / 2
                    val center = lazyRowState.layoutInfo.viewportEndOffset / 2
                    val childCenter = it.offset + it.size / 2
                    val target = childCenter - center
                    if (target in -delta..delta) return@derivedStateOf index
                }
                null
            }
        }

        val animatedAlpha by animateFloatAsState(
            targetValue = if (index == selected) 1.0f else 0.5f,
            animationSpec = tween(250),
            label = "alpha"
        )

        val animatedSize by animateDpAsState(
            targetValue = if (index == selected) 96.dp else 80.dp,
            animationSpec = tween(250),
            label = "alpha"
        )

        Surface(
            modifier = Modifier
                .alpha(
                    alpha = animatedAlpha
                )
                .size(
                    size = animatedSize
                )
                .padding(horizontal = 4.dp),
            shape = RoundedCornerShape(20.dp),
            color = if (index == selected)
                viewModel.listOfLetters[index % viewModel.letters.size].color
            else
                viewModel.listOfLetters[index % viewModel.letters.size].color.copy(
                    alpha = 0.25f
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModel.listOfLetters[index % viewModel.letters.size].letter,
                    fontSize = 64.sp,
                    color = Color.White,
                )
            }
        }
    }
}