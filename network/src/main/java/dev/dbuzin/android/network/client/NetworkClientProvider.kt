package dev.dbuzin.android.network.client

import dev.dbuzin.android.network.cache.TokenProvider
import dev.dbuzin.android.network.data.model.common.TokenPair
import dev.dbuzin.android.network.data.model.request.RefreshRequest
import javax.inject.Inject

internal class NetworkClientProvider @Inject constructor(
    private val tokenProvider: TokenProvider
) {
    val httpClient = NetworkClient(
        tokenProvider = tokenProvider,
        refresh = ::refreshTokens
    )

    private suspend fun refreshTokens(): TokenPair {
        return httpClient.postData(
            path = REFRESH_PATH,
            type = EndPointType.AUTH,
            requestBody = RefreshRequest(
                refreshToken = tokenProvider.getTokenPair().refreshToken
            )
        )
    }

    private companion object {
        const val REFRESH_PATH = "/refresh"
    }
}