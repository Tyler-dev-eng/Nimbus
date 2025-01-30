# Weather App

## Overview
This app retrieves and displays real-time weather data using the device's location. It showcases key weather information, including temperature for different times of the day (morning, afternoon, evening, and night), and visualises this data using Canvas. The app is designed to demonstrate efficient data fetching and UI updates.

## Features
1. **Real-Time Weather Data**: Fetches and displays the current weather based on the user's location.
2. **Temperature Visualisation**: Uses the built-in canvas to display temperature variations throughout the day.
3. **Location-Based Fetching**: Automatically retrieves weather data using the device's GPS.

## Architecture
The app follows **Clean Architecture** principles for better maintainability and scalability. It consists of:
1. **Domain Layer**: Contains business logic and data models.
2. **Data Layer**: Handles API requests to fetch weather data.
3. **Presentation Layer**: Manages UI components and user interactions.

## API Setup
This app uses the Open-Meteo API (https://open-meteo.com/) to fetch weather data. API calls are structured to request temperature details for different times of the day.

## Tutorial Source
This app was built following a tutorial by Philipp Lackner (https://www.youtube.com/watch?v=eAbKK7JNxCE&ab_channel=PhilippLackner). You can follow the tutorial to learn more about building similar apps with Android.

## Screenshots
<div style="display: flex; justify-content: space-evenly; gap: 20px;">
  <img src="https://github.com/Tyler-dev-eng/StockLens/blob/main/Screenshot_20250128_132112.png" width="250" height="550" />
  <img src="https://github.com/Tyler-dev-eng/StockLens/blob/main/Screenshot_20250128_132238.png" width="250" height="550" />
  <img src="https://github.com/Tyler-dev-eng/StockLens/blob/main/Screenshot_20250128_132305.png" width="250" height="550" />
</div>
