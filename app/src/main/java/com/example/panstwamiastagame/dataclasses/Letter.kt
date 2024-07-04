package com.example.panstwamiastagame.dataclasses

import androidx.compose.ui.graphics.Color
import java.util.UUID

data class Letter(
    val letter: String,
    var selected: Boolean = false,
    var isDrawn: Boolean = false,
    var color: Color = Color(0xffEF9C66)
)
