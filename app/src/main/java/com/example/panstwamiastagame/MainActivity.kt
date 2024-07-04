package com.example.panstwamiastagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.panstwamiastagame.navigation.MainNavigation
import com.example.panstwamiastagame.ui.theme.PanstwaMiastaGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PanstwaMiastaGameTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                )
                { innerPadding ->
                    MainNavigation(
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}