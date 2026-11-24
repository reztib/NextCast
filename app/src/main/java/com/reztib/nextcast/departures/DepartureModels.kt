// TODO: Sauberes UI-Datenmodell "Departure" erstellen (lineNumber, destination, minutesRemaining, delayMinutes)
// TODO: Hilfsfunktion schreiben, die ISO-Zeitstempel (Planned vs. Estimated) in Restminuten & Verspätung umrechnet
package com.reztib.nextcast.departures

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VrnDmResponse(
    val stopEvents: List<StopEvent> = emptyList()
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class StopEvent(
    val departureTimePlanned: String,
    val departureTimeEstimated: String? = null, // Kann fehlen, wenn es keine Echtzeitdaten gibt
    val transportation: Transportation
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Transportation(
    val disassembledName: String? = null,   // Linie, z. B. "101" oder "S1"
    val description: String? = null,        // Strecke / Linienverlauf
    val destination: Destination
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Destination(
    val name: String? = null
)

