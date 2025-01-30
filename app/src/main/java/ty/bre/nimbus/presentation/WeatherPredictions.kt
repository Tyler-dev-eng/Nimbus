package ty.bre.nimbus.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ty.bre.nimbus.presentation.ui.theme.jetBrainsMono
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherPredictions(
    viewModel: WeatherViewModel,
) {
    val weatherPredictions = viewModel.state.weatherInfo?.weatherDataPerDay
    val currentDate = LocalDate.now()

    if (weatherPredictions != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF87CEEB), Color(0xFF4682B4))
                    )
                )
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(weatherPredictions.entries.toList()) { (dayIndex, weatherDataList) ->
                    val maxTempData = weatherDataList.maxByOrNull { it.temperatureCelsius }
                    val maxTemp = maxTempData?.temperatureCelsius
                    val weatherType = maxTempData?.weatherType

                    val date = weatherDataList.firstOrNull()?.time?.toLocalDate()
                    val formattedDate = date?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

                    if (maxTemp != null && weatherType != null && formattedDate != null) {

                        // Determine the display text based on the date comparison
                        val displayText = when {
                            date.isEqual(currentDate) -> "Today"
                            date.isEqual(currentDate.plusDays(1)) -> "Tomorrow"
                            else -> date.dayOfWeek.getDisplayName(
                                TextStyle.FULL,
                                Locale.getDefault()
                            )
                        }

                        val shortDate = date.format(DateTimeFormatter.ofPattern("dd MMM"))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = displayText,
                                        style = androidx.compose.ui.text.TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = jetBrainsMono,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2C3E50)
                                        ),
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = shortDate,
                                        style = androidx.compose.ui.text.TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = jetBrainsMono,
                                            color = Color.Gray
                                        ),
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "${maxTemp}°C",
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = jetBrainsMono,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF2C3E50),
                                    ),
                                    modifier = Modifier.weight(0.5f)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Image(
                                    painter = painterResource(id = weatherType.iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                    }
                }
            }
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                items(weatherPredictions.entries.toList()) { (dayIndex, weatherDataList) ->
//
//                    val maxTempData = weatherDataList.maxByOrNull { it.temperatureCelsius }
//                    val maxTemp = maxTempData?.temperatureCelsius
//                    val weatherType = maxTempData?.weatherType
//
//                    val date = weatherDataList.firstOrNull()?.time?.toLocalDate()
//                    val formattedDate = date?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
//
//                    if (maxTemp != null && weatherType != null && formattedDate != null) {
//
//                        // Determine the display text based on the date comparison
//                        val displayText = when {
//                            date.isEqual(currentDate) -> "Today"
//                            date.isEqual(currentDate.plusDays(1)) -> "Tomorrow"
//                            else -> date.dayOfWeek.getDisplayName(
//                                TextStyle.FULL,
//                                Locale.getDefault()
//                            )
//                        }
//
//                        val shortDate = date.format(DateTimeFormatter.ofPattern("dd MMM"))
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 8.dp),
//                        ) {
//                            Column(
//                                modifier = Modifier.weight(1f)
//                            ) {
//                                Text(
//                                    text = displayText,
//                                    style = androidx.compose.ui.text.TextStyle(
//                                        fontSize = 16.sp,
//                                        fontFamily = jetBrainsMono
//                                    ),
//                                )
//                                Text(
//                                    text = shortDate,
//                                    fontSize = 14.sp,
//                                    color = Color.Gray
//                                )
//                            }
//                            Spacer(modifier = Modifier.weight(0.1f))
//                            Text(
//                                text = "${maxTemp}°C",
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f)
//                            )
//                            Spacer(modifier = Modifier.weight(0.1f))
//                            Image(
//                                painter = painterResource(id = weatherType.iconRes),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .width(40.dp)
//                                    .height(40.dp)
//                                    .padding(4.dp)
//                            )
//                        }
//                    }
//                }
//            }
        }

    }
}