package com.example.panstwamiastagame.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.panstwamiastagame.dataclasses.Letter
import com.example.panstwamiastagame.dataclasses.Settings
import kotlin.random.Random

@SuppressLint("MutableCollectionMutableState")
class GameViewModel : ViewModel() {
    val letters = listOf(
        "A", "B", "C", "D", "E",
        "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O",
        "P", "R", "S", "T", "U",
        "W", "Z")

    var listOfLetters: MutableList<Letter> = MutableList(letters.size) {
        val color = when(Random.nextInt(0, 3)) {
            0 -> { Color(0xff78ABA8) }
            1 -> { Color(0xffC8CFA0) }
            else -> { Color(0xffEF9C66) }
        }

        Letter(
            letter = letters[it],
            color = color
        )
    }

    var listOfDrawnLetters: MutableList<Letter?> = MutableList(20) {
        null
    }

    var timeOfRound by mutableIntStateOf(30 * 1000)

    var startTimerDown by mutableStateOf(false)

    var randomLetter by mutableStateOf(false)

    var endGame by mutableStateOf(false)

    fun updateSettings(settings: Settings) {
        listOfDrawnLetters = MutableList(settings.rounds!!) { null }
        timeOfRound = settings.time!! * 1000
    }
}