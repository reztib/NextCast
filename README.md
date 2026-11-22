# NextCast

Minimalistisches Android-Dashboard zur Anzeige von lokalem Wetter und Echtzeit-ÖPNV-Abfahrten, optimiert für den 24/7-Kiosk-Betrieb auf einem dedizierten Tablet.

## Funktionen

- **Wetter:** Aktuelle Temperatur, Wetterbedingungen und Tagesvorhersage über die Open-Meteo-API.
- **ÖPNV:** Live-Abfahrtsmonitor inklusive Verspätungsinformationen über die `v6.db.transport.rest`-API.
- **Kiosk-Modus:** Dunkles Vollbild-Interface, dauerhaft aktives Display, automatische Nacht-Dimmung und Unterstützung als Standard-Launcher.

## Tech-Stack

- **Sprache:** Kotlin
- **UI:** Jetpack Compose
- **Netzwerk:** Ktor / Retrofit + kotlinx.serialization
- **APIs:**
    - [Open-Meteo](https://open-meteo.com) (Wetterdaten)
    - [v6.db.transport.rest](https://transport.rest) (ÖPNV / HAFAS)

## Setup

1. Repository klonen und das Projekt in Android Studio öffnen.
2. Eigene Koordinaten sowie die gewünschte Haltestellen-ID in den Konfigurationskonstanten hinterlegen.
3. App bauen und per ADB auf dem Zielgerät installieren.
4. Optional: App auf dem Tablet als Standard-Startbildschirm (Launcher) festlegen.

## Projektstruktur

- `MainActivity.kt` – Einstiegspunkt, Fenster-Flags, Helligkeitssteuerung und Compose-Hauptlayout.
- `AndroidManifest.xml` – Internet-Berechtigung und `CATEGORY_HOME`-Intent-Filter für den Launcher-Modus.
- `build.gradle.kts` – App-Dependencies und SDK-Konfiguration.
