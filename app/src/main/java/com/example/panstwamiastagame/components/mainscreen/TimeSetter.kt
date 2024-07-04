package com.example.panstwamiastagame.components.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.panstwamiastagame.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSetter(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .width(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Czas rundy:",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${viewModel.time.intValue} sekund",
                fontWeight = FontWeight.Bold
            )
        }

        Slider(
            value = viewModel.time.intValue.toFloat(),
            onValueChange = {
                viewModel.time.intValue = it.toInt()
            },
            valueRange = 10f..120f,
            steps = 50,
            colors = SliderDefaults.colors().copy(
                thumbColor = Color(0xffEF9C66),
                activeTrackColor = Color(0xffEF9C66),
                activeTickColor = Color(0xffEF9C66),
                inactiveTickColor = Color.Transparent,
                inactiveTrackColor = Color(0xffEF9C66).copy(
                    alpha = 0.25f
                )
            ),
            track = { sliderState ->
                SliderDefaults.Track(
                    modifier = Modifier
                        .scale(scaleX = 1f, scaleY = 2f),
                    sliderState = sliderState,
                    colors = SliderDefaults.colors().copy(
                        thumbColor = Color(0xffEF9C66),
                        activeTrackColor = Color(0xffEF9C66),
                        activeTickColor = Color(0xffEF9C66),
                        inactiveTickColor = Color.Transparent,
                        inactiveTrackColor = Color(0xffEF9C66).copy(
                            alpha = 0.25f
                        )
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimeSetterPreview() {
    TimeSetter(
        viewModel = MainViewModel()
    )
}