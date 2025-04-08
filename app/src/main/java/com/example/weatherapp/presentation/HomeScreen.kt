package com.example.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.common.Constants.mainBackground
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = mainBackground
                    )
                )
            )

}

@Preview
@Composable
fun PreviewMainScreen(modifier: Modifier = Modifier) {
    WeatherAppTheme {
        HomeScreen()
    }
}