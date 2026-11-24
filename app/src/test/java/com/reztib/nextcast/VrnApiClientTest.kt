package com.reztib.nextcast

import com.reztib.nextcast.departures.tripRequest
import kotlinx.coroutines.runBlocking
import org.junit.Test

@kotlinx.serialization.InternalSerializationApi
class VrnApiClientTest {

    @Test
    fun testTripRequest() = runBlocking {
        tripRequest()
    }
}
