package com.example.panstwamiastagame.components.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionMainScreen(
    title: String,
    content: @Composable() () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SectionMainScreenPreview() {
    SectionMainScreen(title = "Sekcja") {
        Surface(
            modifier = Modifier.size(50.dp)
        ) {

        }
    }
}