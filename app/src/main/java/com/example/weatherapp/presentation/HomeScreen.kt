package com.example.weatherapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.common.Constants.mainBackground
import com.example.weatherapp.domain.model.Location
import com.example.weatherapp.ui.theme.WeatherAppTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val uiState by weatherViewModel.weatherUiState.collectAsState()
    val locationInput by weatherViewModel.location.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = mainBackground
                )
            )
            .padding(16.dp)
            ){
            WeatherContent(
                uiState = uiState,
                locationInput = locationInput,
                onLocationChange = {weatherViewModel.updateLocation(it)},
                onSearchClicked = {weatherViewModel.getWeatherWithLocation(locationInput)}
            )

        if(uiState.isLoading){
            LoadingOverlay()
        }
    }

    if(uiState.isLoading){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun WeatherContent(
    uiState: WeatherUiState,
    locationInput: String,
    onLocationChange: (String)-> Unit,
    onSearchClicked: ()-> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            locationInput = locationInput,
            onLocationChange = onLocationChange,
            onSearchClicked = onSearchClicked
        )
        Spacer(modifier = Modifier.height(16.dp))

        WeatherInfo(uiState = uiState)
    }
}

@Composable
fun SearchBar(
    locationInput: String,
    onLocationChange: (String) -> Unit,
    onSearchClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){

        OutlinedTextField(
            value = locationInput,
            onValueChange = onLocationChange,
            label = { Text(text = "Enter Location")},
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Button(
            onClick = onSearchClicked,
            enabled = locationInput.isNotEmpty()
            ) {
            Text(text = "Search")
        }
    }
}

@Composable
fun WeatherInfo(
    uiState: WeatherUiState,
    modifier: Modifier = Modifier
    ) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when{
            uiState.error != null-> {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            uiState.temperature != null-> {
                Text(
                    text = "Location: ${uiState.temperature.location.name}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Location: ${uiState.temperature.current.temp_c} °C",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else-> {
                Text(
                    text = "Enter a location to see the weather.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun LoadingOverlay(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}
