package ty.bre.nimbus.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ty.bre.nimbus.presentation.ui.theme.jetBrainsMono

@Composable
fun WeatherChart(
    state: WeatherState, modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        val morning = data.find { it.time.toLocalTime().hour == 7 }
        val afternoon = data.find { it.time.toLocalTime().hour == 13 }
        val evening = data.find { it.time.toLocalTime().hour == 19 }
        val night = data.find { it.time.toLocalTime().hour == 1 }

        val labels = listOf("Morning", "Afternoon", "Evening", "Night")

        val temperatureData = listOf(
            Pair(0, morning?.temperatureCelsius?.toFloat() ?: 0f),
            Pair(1, afternoon?.temperatureCelsius?.toFloat() ?: 0f),
            Pair(2, evening?.temperatureCelsius?.toFloat() ?: 0f),
            Pair(3, night?.temperatureCelsius?.toFloat() ?: 0f)
        )

        Card(
            colors = CardColors(
                containerColor = Color(0xFFF0F0F0),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFF5F5F5),
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Text(
                text = "Temperature",
                color = Color.Black,
                fontFamily = jetBrainsMono,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp)
            )

            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                // Define padding and scaling
                val padding = 40f
                val maxTemperature = temperatureData.maxOf { it.second }
                val yScale = (canvasHeight - 2 * padding) / maxTemperature

                // Draw the temperature line with smooth curves
                val path = androidx.compose.ui.graphics.Path().apply {
                    val points = temperatureData.mapIndexed { index, (_, temperature) ->
                        val x =
                            padding + index * (canvasWidth - 2 * padding) / (temperatureData.size - 1)
                        val y = canvasHeight - padding - temperature * yScale
                        Offset(x, y)
                    }

                    // Move to the first point
                    moveTo(points[0].x, points[0].y)

                    // Draw cubic Bézier curves between points for smoothness
                    for (i in 1 until points.size) {
                        val previousPoint = points[i - 1]
                        val currentPoint = points[i]
                        val controlPoint1 = Offset(
                            previousPoint.x + (currentPoint.x - previousPoint.x) * 0.5f,
                            previousPoint.y
                        )
                        val controlPoint2 = Offset(
                            currentPoint.x - (currentPoint.x - previousPoint.x) * 0.5f,
                            currentPoint.y
                        )
                        cubicTo(
                            controlPoint1.x,
                            controlPoint1.y,
                            controlPoint2.x,
                            controlPoint2.y,
                            currentPoint.x,
                            currentPoint.y
                        )
                    }
                }

                // Draw the smoothed path
                drawPath(
                    path = path,
                    color = Color(0xFF4CAF50), // A green color that matches the card's theme
                    style = Stroke(width = 4f)
                )

                // Draw temperature points and labels
                temperatureData.forEachIndexed { index, (_, temperature) ->
                    val x =
                        padding + index * (canvasWidth - 2 * padding) / (temperatureData.size - 1)
                    val y = canvasHeight - padding - temperature * yScale

                    // Draw temperature point (slightly larger and darker)
                    drawCircle(
                        color = Color(0xFFC0C0C0), // A darker gray color for the points
                        radius = 8f, // Slightly larger radius
                        center = Offset(x, y)
                    )

                    // Draw label (Morning, Afternoon, etc.) directly underneath the point
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            labels[index],
                            x + 5,
                            canvasHeight - padding - 40,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = 20f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }

                    // Draw temperature value directly underneath the label
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "${temperature.toInt()}°C",
                            x,
                            canvasHeight - padding,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = 20f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }
                }
            }
        }
    }
}