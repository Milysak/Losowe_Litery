package com.example.panstwamiastagame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.panstwamiastagame.viewmodels.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.panstwamiastagame.R
import com.example.panstwamiastagame.Routes
import com.example.panstwamiastagame.components.mainscreen.RoundSetter
import com.example.panstwamiastagame.components.mainscreen.SectionMainScreen
import com.example.panstwamiastagame.components.mainscreen.TimeSetter

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val mContext = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(96.dp),
                painter = painterResource(id = R.drawable.logo_panstwa_miasta),
                contentDescription = "app's logo"
            )

            SectionMainScreen(
                title = "Ilość rund w grze:"
            ) {
                RoundSetter(
                    viewModel = viewModel,
                    mContext = mContext
                )
            }

            SectionMainScreen(
                title = ""
            ) {
                TimeSetter(
                    viewModel = viewModel
                )
            }

            Button(
                onClick = {
                    navController.navigate(Routes.GameScreen.name + "/${viewModel.rounds.intValue}/${viewModel.time.intValue}")
                },
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = Color(0xff78ABA8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "START")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}