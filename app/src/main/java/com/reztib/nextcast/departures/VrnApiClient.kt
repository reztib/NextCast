// TODO: JSON-Antwort in eine sortierte Liste von UI-Departure-Objekten mappen

/*
* - unter stopEvents beginnend mit Index 0 sind Fahrten aufgelistet, dort die Felder:
*   - departureTimePlanned (geplante Abfahrtszeit)
*   - departureTimeEstimated (voraussichtliche Abfahrtszeit, für Differenzberechung ("+2) oder so
*   - dann unter stopEvents.transportation die Felder:
        - disassembledName für die Linie
        - description für die Strecke

* - Fahrten Richtung Endhaltestelle rausfiltern:
* - was bei stopEvents.transportation.destination.name den Ortsnamen enthält,
*       über Kotlin filtern
 */


package com.reztib.nextcast.departures

import com.reztib.nextcast.BuildConfig
import com.reztib.nextcast.network.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpMethod
import kotlin.coroutines.cancellation.*

private val stopId = BuildConfig.VRN_STOP_ID
private val excludeDirection = BuildConfig.VRN_EXCLUDE_DIRECTION
private val urlEndpoint = "https://trias.vrn.de/vrn/XML_DM_REQUEST?commonMacro=dm&outputFormat=RapidJSON&type_dm=any&name_dm=${stopId}&mode=direct&useRealtime=1&locationServerActive=1"

suspend fun tripRequest() {
    try {
        val response = HttpClientProvider.client.get("https://trias.vrn.de/vrn/XML_DM_REQUEST") {
            parameter("commonMacro", "dm")
            parameter("outputFormat", "RapidJSON")
            parameter("type_dm", "stop")
            parameter("name_dm", stopId)
            parameter("mode", "direct")
            parameter("useRealtime", 1)
            parameter("locationServerActive", 1)
        }

        if (response.status.value in 200..299) {
            println(response.bodyAsText())
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        println(e.message)
    }

}