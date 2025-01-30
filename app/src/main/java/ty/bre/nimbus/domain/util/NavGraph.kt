package ty.bre.nimbus.domain.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ty.bre.nimbus.domain.weather.MainScreen
import ty.bre.nimbus.presentation.WeatherPredictions
import ty.bre.nimbus.presentation.WeatherViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen,
    viewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.FirstScreen> {
            MainScreen(navController = navController, viewModel = viewModel)
        }

        composable<Screen.SecondScreen> {
            WeatherPredictions(viewModel = viewModel)
        }
    }
}