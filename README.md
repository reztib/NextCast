# NextCast

Minimalistisches Android-Dashboard zur Anzeige von lokalem Wetter und Echtzeit-ÖPNV-Abfahrten, optimiert für den 24/7-Kiosk-Betrieb auf einem dedizierten Tablet.

## Funktionen

- **Wetter:** Aktuelle Temperatur, Wetterbedingungen und Tagesvorhersage über die Open-Meteo-API.
- **ÖPNV:** Live-Abfahrtsmonitor inklusive Echtzeit-Prognosen und Richtungsfilter über die offizielle VRN Open-Service-API (RapidJSON).
- **Kiosk-Modus:** Dunkles Vollbild-Interface, dauerhaft aktives Display, automatische Nacht-Dimmung und Unterstützung als Standard-Launcher.

## Tech-Stack

- **Sprache:** Kotlin
- **UI:** Jetpack Compose
- **Netzwerk:** Ktor Client + kotlinx.serialization
- **APIs:**
  - [Open-Meteo](https://open-meteo.com) (Wetterdaten)
  - [VRN Open Data / RapidJSON](https://vrn.de) (ÖPNV / EFA)

## Projektstruktur

- `MainActivity.kt` – Einstiegspunkt, Fenster-Flags, Helligkeitssteuerung und Compose-Hauptlayout.
- `network/`
  - `HttpClientProvider.kt` – Zentral konfigurierter Ktor-Client mit JSON-Serializer und Timeouts.
- `departures/`
  - `DepartureModels.kt` – Datenmodelle (DTOs & UI-Models) sowie Zeit- und Verspätungsberechnung.
  - `VrnApiClient.kt` – Abruf und Richtungsfilterung der VRN-Abfahrtsdaten (`XML_DM_REQUEST`).
  - `DepartureBoardScreen.kt` – Compose-UI für die Abfahrtstafel mit automatischer Aktualisierung.
- `weather/`
  - `WeatherModels.kt` – Datenmodelle und Mapping der WMO-Wettercodes auf Icons/Texte.
  - `OpenMeteoClient.kt` – Abruf der lokalen Wetterdaten.
  - `WeatherWidget.kt` – Compose-Widget für die Wetteranzeige.
- `AndroidManifest.xml` – Internet-Berechtigung und `CATEGORY_HOME`-Intent-Filter für den Launcher-Modus.
- `build.gradle.kts` – App-Dependencies und SDK-Konfiguration.
