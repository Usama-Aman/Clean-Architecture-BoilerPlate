package com.sam.cleanarchitecturekickstart.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Inject

class KtorClient @Inject constructor() {

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }

    fun getHttpClient() = HttpClient(Android) {
        install(DefaultRequest) {
//            url(Constants.BASE_URL)
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
//            header("api-key", BuildConfig.API_KEY)
//            header(
//                HttpHeaders.Authorization,
//                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMTJiNThlODEwOWJjNDI2YmE2MzNiMTk4ZTdlY2UwZiIsInN1YiI6IjY1N2YyZTdlZWE4NGM3MTY1ZWI4NTIwNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WcXw2tU1AWtm_jbZgPFP_G3LhGUuSU9-CbfdwqGBvTg"
//            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("HttpClient", message)
                }
            }
        }
        install(ContentNegotiation) {
            json()
        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }


//            install(Auth) {
//                bearer {
//                    refreshTokens {
//                        val token = client.get {
//                            markAsRefreshTokenRequest()
//                            url("refreshToken")
//                            parameter("refreshToken", localService.getRefreshToken())
//                        }.body<Token>()
//                        BearerTokens(
//                            accessToken = token.bearerToken,
//                            refreshToken = token.refreshToken
//                        )
//                    }
//                }
//            }
    }


}