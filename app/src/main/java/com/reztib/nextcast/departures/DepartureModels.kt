// TODO: Sauberes UI-Datenmodell "Departure" erstellen (lineNumber, destination, minutesRemaining, delayMinutes)
package com.reztib.nextcast.departures

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant

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

private val timeFormatter  = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault())

fun formatToGermanTime(utcString: String): String {
    val instant = Instant.parse(utcString)
    return timeFormatter.format(instant)
}

