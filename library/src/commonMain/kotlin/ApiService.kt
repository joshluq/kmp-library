package io.github.kotlin.fibonacci

import io.ktor.client.call.*
import io.ktor.client.request.*

class ApiService {

    private val client = createHttpClient(httpClientEngine())

    suspend fun fetchData(): String {
        return try {
            val response = client.get("https://jsonplaceholder.typicode.com/todos/1")
            response.body<String>()
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
            ""
        }
    }

}
