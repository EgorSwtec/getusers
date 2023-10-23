package com.example.getusers

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class GetUsersApi {

    companion object {
        private const val GET_USERS_URL = "https://api.github.com/users"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUsersInfo(): List<UserInfo> {
        return try {
            httpClient.get(GET_USERS_URL).body()
        } catch (exc: Exception) {
            emptyList()
        }
    }
}