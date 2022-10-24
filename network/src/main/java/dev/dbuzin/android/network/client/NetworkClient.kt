package dev.dbuzin.android.network.client

import dev.dbuzin.android.network.cache.TokenProvider
import dev.dbuzin.android.network.data.model.common.ResponseException
import dev.dbuzin.android.network.data.model.common.TokenPair
import dev.dbuzin.android.network.data.model.response.ErrorResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class NetworkClient(
    private val tokenProvider: TokenProvider,
    private val refresh: suspend () -> TokenPair
) {

    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = false
                    prettyPrint = true
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(Auth) {
            bearer {
                loadTokens {
                    val tokens = tokenProvider.getTokenPair()
                    BearerTokens(
                        accessToken = tokens.accessToken,
                        refreshToken = tokens.refreshToken
                    )
                }
                refreshTokens {
                    val updatedTokens = refresh()
                    BearerTokens(
                        accessToken = updatedTokens.accessToken,
                        refreshToken = updatedTokens.refreshToken
                    )
                }
            }
        }

        HttpResponseValidator {
            validateResponse { response ->
                val body: ErrorResponse = response.body()
                throw ResponseException(body.message)
            }
        }

        defaultRequest {
            header("Content-Type", "application/json")
            accept(ContentType.parse("application/json"))
        }
    }

    suspend inline fun <reified T> getData(
        path: String,
        type: EndPointType,
        params: Map<String, Any>? = null
    ): T {
        val host = type.hostByType()
        return httpClient.get(
            urlString = "$host$path",
            block = {
                params?.let {
                    it.forEach { param ->
                        parameter(param.key, param.value)
                    }
                }
            }
        ).body()
    }

    suspend inline fun <reified T, reified R> postData(
        path: String,
        type: EndPointType,
        requestBody: T
    ): R {
        val host = type.hostByType()
        return httpClient.post(
            urlString = "$host$path",
            block = {
                setBody(requestBody)
            }
        ).body()
    }

    fun EndPointType.hostByType(): String = when (this) {
        EndPointType.AUTH -> AUTH_ENDPOINT
        EndPointType.BEER -> BEER_ENDPOINT
        EndPointType.DICTIONARIES -> DICTIONARIES_ENDPOINT
        EndPointType.REVIEWS -> REVIEWS_ENDPOINT
    }

    private companion object {
        const val BASE_PATH = "http://194.87.111.160:80/api/v1"
        const val AUTH_ENDPOINT = "$BASE_PATH/auth"
        const val BEER_ENDPOINT = "$BASE_PATH/beer"
        const val DICTIONARIES_ENDPOINT = "$BASE_PATH/dictionaries"
        const val REVIEWS_ENDPOINT = "$BASE_PATH/reviews"
    }
}

enum class EndPointType {
    AUTH,
    BEER,
    DICTIONARIES,
    REVIEWS
}