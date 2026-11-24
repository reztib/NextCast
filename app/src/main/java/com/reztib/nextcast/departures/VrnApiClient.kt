// TODO: Basis-URL der VRN-API und ermittelte Haltestellen-ID (de:07312:...) als Konstanten definieren
// TODO: Suspend-Funktion fetchDepartures() schreiben, die XML_DM_REQUEST über HttpClientProvider aufruft
// TODO: Fehlerbehandlung (try-catch) einbauen, damit die Kiosk-App bei WLAN-Aussetzern nicht abstürzt
// TODO: JSON-Antwort in eine sortierte Liste von UI-Departure-Objekten mappen
package com.reztib.nextcast.departures

import com.reztib.nextcast.BuildConfig

private val stopId = BuildConfig.VRN_STOP_ID
private val excludeDirection = BuildConfig.VRN_EXCLUDE_DIRECTION

/*
* - Link für Abfahrten in beide Richtungen (!)
* - https://trias.vrn.de/vrn/XML_DM_REQUEST?commonMacro=dm&outputFormat=RapidJSON&type_dm=any&name_dm=$stopId&mode=direct&useRealtime=1&locationServerActive=1
* * - unter stopEvents beginnend mit Index 0 sind Fahrten aufgelistet, dort die Felder:
* - departureTimePlanned (geplante Abfahrtszeit)
* - departureTimeEstimated (voraussichtliche Abfahrtszeit, für Differenzberechung ("+2) oder so
* - dann unter stopEvents.transportation die Felder:
    - disassembledName für die Linie
    - description für die Strecke

* - Fahrten Richtung Endhaltestelle rausfiltern:
* - was bei stopEvents.transportation.destination.name den Ortsnamen enthält,
*       über Kotlin filtern
 */

