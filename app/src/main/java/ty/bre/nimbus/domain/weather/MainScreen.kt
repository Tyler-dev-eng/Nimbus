package ty.bre.nimbus.domain.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import ty.bre.nimbus.presentation.WeatherCard
import ty.bre.nimbus.presentation.WeatherForecast
import ty.bre.nimbus.presentation.WeatherViewModel
import ty.bre.nimbus.presentation.ui.theme.jetBrainsMono

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: WeatherViewModel,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF87CEEB), Color(0xFF4682B4))
                    )
                )
                .verticalScroll(rememberScrollState())
        ) {
            WeatherCard(
                state = viewModel.state,
                backgroundColour = Color(0xFFF5F5F5),
            )

            WeatherForecast(state = viewModel.state, navController = navController)
        }
        if (viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = Color.White
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = jetBrainsMono,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}