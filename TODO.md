# Tablet-Dashboard (Wetter & ÖPNV) – Projekt-ToDo

## Phase 1: Setup & Vorbereitung
- [X] **Android Studio** installieren und ein neues Projekt (*Empty Activity* mit Kotlin & Jetpack Compose) anlegen
- [X] **Minimum SDK** passend zur Android-Version des Samsung-Tablets wählen
- [X] Tablet per USB/ADB verbinden und testen, ob die leere Starter-App auf dem Gerät startet
- [ ] **Berechtigungen & Dependencies** einrichten:
    - [X] `<uses-permission android:name="android.permission.INTERNET" />` in der `AndroidManifest.xml` ergänzen
    - [X] HTTP-Client & JSON-Parser (z. B. *Ktor* oder *Retrofit* + *kotlinx.serialization*) in `build.gradle.kts` hinzufügen
- [ ] **API-Endpunkte im Browser testen:**
    - [ ] Eigene **Haltestellen-ID** über `v6.db.transport.rest/locations?query=...` heraussuchen
    - [ ] Abfahrts-Endpoint (`/stops/{ID}/departures`) prüfen und relevante JSON-Felder notieren (`line.name`, `direction`, `when`, `delay`)
    - [ ] **Open-Meteo URL** mit eigenen Koordinaten (z. B. `latitude=49.44&longitude=7.77`) testen (`temperature_2m`, `weather_code`, Tageshöchst-/Tiefstwerte)

---

## Phase 2: Datenanbindung (Backend-Logik in Kotlin)
- [ ] Datenmodelle (`data class`) für **Wetter** und **Abfahrten** anlegen
- [ ] Netzwerk-Service schreiben, der die JSON-Daten asynchron (mit Kotlin Coroutines) abruft
- [ ] **Fehlerbehandlung einbauen:** App darf bei kurzem WLAN-Ausfall oder API-Timeout nicht abstürzen (stattdessen alten Stand behalten oder Hinweis anzeigen)
- [ ] **Auto-Refresh implementieren:**
    - [ ] ÖPNV-Daten alle **60 Sekunden** aktualisieren
    - [ ] Wetterdaten alle **15–30 Minuten** aktualisieren

---

## Phase 3: Benutzeroberfläche (Jetpack Compose)
- [ ] **Layout-Grundgerüst** im Querformat (Landscape) mit dunklem Hintergrund (`Color.Black` / Dark Mode) erstellen
- [ ] **Linke / Obere Sektion (Zeit & Wetter):**
    - [ ] Große Digitaluhr und aktuelles Datum anzeigen
    - [ ] Aktuelle Temperatur + Wetter-Icon (passend zum Open-Meteo `weather_code`) darstellen
    - [ ] Optional: Kurze Vorschau für die nächsten Stunden / Tages-Min & Max
- [ ] **Rechte / Untere Sektion (ÖPNV-Abfahrtsmonitor):**
    - [ ] Liste (`LazyColumn`) mit den nächsten Abfahrten bauen
    - [ ] Liniennummer (farbig hervorgehoben), Zielhaltestelle und verbleibende Minuten (`in X Min.`) anzeigen
    - [ ] **Verspätungen** farblich markieren (z. B. grün = pünktlich, rot = `+X Min.`)

---

## Phase 4: Kiosk-Modus & 24/7-Dauerbetrieb
- [ ] **Display wachhalten:** `FLAG_KEEP_SCREEN_ON` in der `MainActivity` aktivieren
- [ ] **Status- & Navigationsleiste ausblenden** (echtes Vollbild / *Immersive Mode*)
- [ ] **Nachtmodus / Auto-Dimmer:** Zwischen z. B. 23:00 und 06:00 Uhr Display-Helligkeit per Code auf Minimum reduzieren (oder schwarzen Screensaver mit Uhr zeigen)
- [ ] **App als Launcher (Home-App) registrieren:** `CATEGORY_HOME` in der `AndroidManifest.xml` ergänzen, damit das Dashboard beim Start/Home-Button immer aktiv bleibt
- [ ] **Akku-Schutz einrichten:** Ladelimit per ADB/Skript oder Smart-Plug (z. B. zwischen 20 % und 80 % halten)

---

## Phase 5: Optionale Ideen für später
- [ ] Mehrere Haltestellen oder Filter nach Fahrtrichtung einbauen
- [ ] Regenradar-Karte oder Unwetterwarnungen (DWD) integrieren
- [ ] Abfallkalender-Erinnerung (z. B. „Morgen Gelber Sack“) als Banner einblenden
- [ ] Bewegungserkennung über die Frontkamera, um das Display nur bei Anwesenheit aufzuwecken
