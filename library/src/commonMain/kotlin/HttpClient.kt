package io.github.kotlin.fibonacci

import io.ktor.client.*
import io.ktor.client.engine.* // Necesario para la factoría del motor
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) { // Pasa el motor específico de la plataforma
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true // Útil para evitar errores si el backend añade nuevos campos
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    defaultRequest {
        header("Content-Type", "application/json")
    }
}

expect fun httpClientEngine(): HttpClientEngine
