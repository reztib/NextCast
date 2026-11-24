// TODO: Koordinaten für Kaiserslautern (lat = 49.4401, lon = 7.7491) als Konstanten hinterlegen
// TODO: Suspend-Funktion fetchCurrentWeather() für den Open-Meteo-Endpoint bauen
// TODO: Fehlerbehandlung (try-catch) hinzufügen und Ergebnis in WeatherInfo umwandeln
package com.reztib.nextcast.weather

import com.reztib.nextcast.BuildConfig

private val latitude = BuildConfig.WEATHER_LAT
private val longitude = BuildConfig.WEATHER_LON