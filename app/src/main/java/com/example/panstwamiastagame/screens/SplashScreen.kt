package com.example.panstwamiastagame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.panstwamiastagame.R
import com.example.panstwamiastagame.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(1100)
        navController.popBackStack()
        navController.navigate(Routes.MainScreen.name)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 108.dp)
                .padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.logo_panstwa_miasta),
            contentDescription = "app's logo"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Losowe Litery",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}