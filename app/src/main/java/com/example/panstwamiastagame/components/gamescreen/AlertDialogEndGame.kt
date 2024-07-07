package com.example.panstwamiastagame.components.gamescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.panstwamiastagame.R

@Composable
fun AlertDialogEndGame(
    endGame: Boolean,
    navController: NavController
) {
    AnimatedVisibility(
        visible = endGame
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AlertDialog(
                onDismissRequest = { /*DO NOTHING*/ },
                confirmButton = {
                    Button(
                        onClick = {
                            navController.popBackStackOrIgnore()
                        },
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color(0xff78ABA8),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "DALEJ")
                    }
                },
                icon = {
                    Image(
                        modifier = Modifier.padding(top = 16.dp).size(100.dp),
                        painter = painterResource(id = R.drawable.logo_panstwa_miasta),
                        contentDescription = "logo"
                    )
                },
                title = {
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp),
                        text = "KONIEC GRY",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 32.dp),
                        text = "Aby przejść do menu głównego kliknij DALEJ.",
                        fontSize = 16.sp
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun AlertDialogEndGamePreview() {
    AlertDialogEndGame(endGame = true, navController = rememberNavController())
}

fun NavController.popBackStackOrIgnore() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}