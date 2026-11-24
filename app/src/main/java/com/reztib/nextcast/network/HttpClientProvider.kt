// TODO: Singleton-Objekt (object HttpClientProvider) mit Ktor HttpClient(Android) anlegen
// TODO: ContentNegotiation mit kotlinx.serialization installieren (ignoreUnknownKeys = true)
// TODO: HttpTimeout-Plugin für stabilen Kiosk-Betrieb konfigurieren (z. B. 10 Sekunden)
package com.reztib.nextcast.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientProvider {
    val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
            connectTimeoutMillis = 10_000
        }
        expectSuccess = true
    }
}