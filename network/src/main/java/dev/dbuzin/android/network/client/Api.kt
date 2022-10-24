package dev.dbuzin.android.network.client

import dev.dbuzin.android.network.data.model.common.TokenPair
import dev.dbuzin.android.network.data.model.request.AuthRequest
import javax.inject.Inject

internal class Api @Inject constructor(
    private val clientProvider: NetworkClientProvider
) {
    private val httpClient = clientProvider.httpClient

    suspend fun authenticate(
        body: AuthRequest
    ) = httpClient.postData<AuthRequest, TokenPair>(
        path = AUTH_PATH,
        type = EndPointType.AUTH,
        requestBody = body
    )

    private companion object {
        const val AUTH_PATH = "/signin"
    }
}