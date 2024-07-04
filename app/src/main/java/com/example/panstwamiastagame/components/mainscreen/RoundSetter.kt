package com.example.panstwamiastagame.components.mainscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panstwamiastagame.viewmodels.MainViewModel

@Composable
fun RoundSetter(
    viewModel: MainViewModel,
    mContext: Context
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallFloatingActionButton(
            onClick = {
                if(viewModel.rounds.intValue > 5)
                    viewModel.minusRound()
                else
                    Toast.makeText(mContext, "Nie można mniej!", Toast.LENGTH_SHORT).show()
            },
            containerColor = Color(0xffEF9C66),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        Text(
            text = viewModel.rounds.intValue.toString(),
            fontSize = 20.sp
        )

        SmallFloatingActionButton(
            onClick = {
                if(viewModel.rounds.intValue < 20)
                    viewModel.addRound()
                else
                    Toast.makeText(mContext, "Nie można więcej!", Toast.LENGTH_SHORT).show()
            },
            containerColor = Color(0xffEF9C66),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}