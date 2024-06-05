package com.aj.myrecipeapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aj.myrecipeapp.ui.theme.MyRecipeAppTheme

@Composable
fun SecondScreen(name:String,navigateToFirstScreen: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "this is the second screen", fontSize = 24.sp)
        Text(text = "Welcome $name", fontSize = 20.sp)

        Button(onClick = { navigateToFirstScreen(name) }) {
            Text(text = "Go to third screen")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    MyRecipeAppTheme {
        SecondScreen("Ajay",{})
    }
}