// TODO: JSON-Antwort in eine sortierte Liste von UI-Departure-Objekten mappen

/*
* - unter stopEvents beginnend mit Index 0 sind Fahrten aufgelistet, dort die Felder:
*   - departureTimePlanned (geplante Abfahrtszeit)
*   - departureTimeEstimated (voraussichtliche Abfahrtszeit, für Differenzberechung ("+2) oder so
*   - dann unter stopEvents.transportation die Felder:
        - disassembledName für die Linie
        - description für die Strecke
        - destination für Ziel


* - Fahrten Richtung Endhaltestelle rausfiltern:
* - was bei stopEvents.transportation.destination.name den Ortsnamen enthält,
*       über Kotlin filtern
 */
@file:OptIn(ExperimentalTime::class)

package com.reztib.nextcast.departures

import com.reztib.nextcast.BuildConfig
import com.reztib.nextcast.network.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.*
import kotlinx.io.IOException
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import java.time.Duration
import kotlin.coroutines.cancellation.*
import kotlin.time.ExperimentalTime
import java.time.Instant

private val stopId = BuildConfig.VRN_STOP_ID
private val excludeDirection = BuildConfig.VRN_EXCLUDE_DIRECTION

@InternalSerializationApi
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
            val data = response.body<VrnDmResponse>()

            val nextThreeEvents = data.stopEvents
                .filterNot { event ->
                    val destinationName = event.transportation.destination.name.orEmpty()
                    excludeDirection.isNotBlank() && destinationName.contains(excludeDirection, ignoreCase = true)

                }
                .take(3)

            println("Abfahrten ab Schlehweg:")
            nextThreeEvents.forEach { event ->
                val line = event.transportation.disassembledName ?: "?"
                val destination = event.transportation.destination?.name ?: "Unbekanntes Ziel"
                val plannedTime = formatToGermanTime(event.departureTimePlanned)
                val estimatedTime = event.departureTimeEstimated?.let { formatToGermanTime(it) } ?: "keine Echtzeit"

                val delayMinutes = event.departureTimeEstimated?.let {estimatedStr ->
                    val plannedInstant = Instant.parse(event.departureTimePlanned)
                    val estimatedInstant = Instant.parse(estimatedStr)
                    Duration.between(plannedInstant, estimatedInstant).toMinutes()
                }

                val delayText = if (delayMinutes != null && delayMinutes >= 2) {
                    " | +$delayMinutes"
                } else {
                    ""
                }

                println("$line -> $destination | $plannedTime$delayText")
            }
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        Result.failure<Exception>(e)
        // Netzwerkprobleme / Timeout
    } catch (e: ResponseException) {
        Result.failure<Exception>(e)
        // HTTP-Error 4xx / 5xx
    } catch (e: SerializationException) {
        Result.failure<Exception>(e)
        // JSON passt nicht
    } catch (e: Exception) {
        //Result.failure<Exception>(e)
        e.printStackTrace()
    }
}