# NextCast

Minimalistisches Android-Dashboard zur Anzeige von lokalem Wetter und Echtzeit-ÖPNV-Abfahrten, optimiert für den 24/7-Kiosk-Betrieb auf einem dedizierten Tablet.

## Funktionen

- **Wetter:** Aktuelle Temperatur, Wetterbedingungen und Tagesvorhersage über die Open-Meteo-API.
- **ÖPNV:** Live-Abfahrtsmonitor inklusive Echtzeit-Prognosen und Richtungsfilter über die offizielle VRN Open-Service-API (RapidJSON).
- **Kiosk-Modus:** Dunkles Vollbild-Interface, dauerhaft aktives Display, automatische Nacht-Dimmung und Unterstützung als Standard-Launcher.

## Setup

1. **Repository klonen:**
   ```bash
   git clone https://github.com<reztib/NextCast.git
   ```
   und das Projekt in **Android Studio** öffnen.

2. **Lokale Konfiguration anlegen:**
   Öffne die Datei `local.properties` im Hauptverzeichnis (wird von Android Studio automatisch erzeugt und über `.gitignore` nicht mit hochgeladen) und ergänze deine eigenen Standort- und Haltestellendaten:

   ```properties
   # VRN-Haltestellen-ID (über XML_STOPFINDER_REQUEST ermittelbar)
   VRN_STOP_ID=de:07312:00000

   # Optional: Zielrichtung ausschließen (z. B. Gegenrichtung / Endhaltestelle)
   VRN_EXCLUDE_DIRECTION=

   # Koordinaten für die Open-Meteo-Wettervorhersage
   WEATHER_LAT=[Breitengrad]
   WEATHER_LON=[Längengrad]
   ```

3. **Projekt synchronisieren & bauen:**
   In Android Studio **Sync Now** (Gradle) ausführen, die App kompilieren und per USB oder WLAN-Debugging (`adb`) auf dem Ziel-Tablet installieren.

4. **Kiosk-Modus aktivieren (Optional):**
   Auf dem Tablet in den Android-Systemeinstellungen **NextCast** als Standard-Startbildschirm (*Home-App / Launcher*) festlegen und bei Bedarf Bildschirm-Pinning aktivieren.


## Tech-Stack

- **Sprache:** Kotlin
- **UI:** Jetpack Compose
- **Netzwerk:** Ktor Client + kotlinx.serialization
- **APIs:**
  - [Open-Meteo](https://open-meteo.com) (Wetterdaten)
  - [VRN Open Data / RapidJSON](https://vrn.de) (ÖPNV / EFA)

## Projektstruktur

```text
app/src/main/
├── AndroidManifest.xml                  # Internet-Berechtigung & Launcher-Intent (CATEGORY_HOME)
└── java/com/reztib/nextcast/
    ├── MainActivity.kt                  # Einstiegspunkt, Kiosk-Flags, Dimmung & Hauptlayout
    ├── network/
    │   └── HttpClientProvider.kt        # Zentraler Ktor-Client (JSON-Serializer & Timeouts)
    ├── departures/
    │   ├── DepartureModels.kt           # Datenmodelle (DTO/UI) & Restminuten-Berechnung
    │   ├── VrnApiClient.kt              # VRN-Abfrage (XML_DM_REQUEST) & Richtungsfilter
    │   └── DepartureBoardScreen.kt      # Compose-UI für die Live-Abfahrtstafel
    └── weather/
        ├── WeatherModels.kt             # Datenmodelle & WMO-Wettercode-Mapping
        ├── OpenMeteoClient.kt           # Abruf der lokalen Wetterdaten (Open-Meteo)
        └── WeatherWidget.kt             # Compose-UI für das Wetter-Widget
```
