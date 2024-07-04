package com.example.panstwamiastagame.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var rounds = mutableIntStateOf(5)

    var time = mutableIntStateOf(30)

    fun addRound() {
        rounds.intValue++
    }

    fun minusRound() {
        rounds.intValue--
    }
}