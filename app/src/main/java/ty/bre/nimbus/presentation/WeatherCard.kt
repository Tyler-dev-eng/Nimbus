package ty.bre.nimbus.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ty.bre.nimbus.R
import ty.bre.nimbus.presentation.ui.theme.jetBrainsMono
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColour: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            colors = CardColors(
                containerColor = backgroundColour,
                contentColor = Color.White,
                disabledContainerColor = backgroundColour,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color(0xFF333333))
                        )

                        Spacer(Modifier.width(5.dp))

                        Text(
                            text = state.locationName ?: "",
                            style = TextStyle(
                                fontFamily = jetBrainsMono,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xFF333333)
                            ),
                        )
                    }

                    Text(
                        text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                        style = TextStyle(
                            fontFamily = jetBrainsMono,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFF333333)
                        ),
                    )
                }

                Spacer(Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(150.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "${data.temperatureCelsius}°C",
                    style = TextStyle(
                        fontFamily = jetBrainsMono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        color = Color(0xFF333333)
                    ),
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = data.weatherType.weatherDesc,
                    style = TextStyle(
                        fontFamily = jetBrainsMono,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color(0xFF333333)
                    ),
                )

                Spacer(Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDateDisplay(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconColor = Color(0xFF333333),
                        textStyle = TextStyle(
                            color = Color(0xFF333333),
                            fontFamily = jetBrainsMono,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    WeatherDateDisplay(
                        value = data.humidity.toInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconColor = Color(0xFF333333),
                        textStyle = TextStyle(
                            color = Color(0xFF333333),
                            fontFamily = jetBrainsMono,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    WeatherDateDisplay(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconColor = Color(0xFF333333),
                        textStyle = TextStyle(
                            color = Color(0xFF333333),
                            fontFamily = jetBrainsMono,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(Modifier.height(16.dp))

                WeatherChart(state = state)
            }
        }
    }
}