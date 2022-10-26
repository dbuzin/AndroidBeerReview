package dev.dbuzin.android.network.client

import dev.dbuzin.android.network.data.model.common.TokenPair
import dev.dbuzin.android.network.data.model.request.AuthRequest
import dev.dbuzin.android.network.data.model.request.RegistrationRequest
import dev.dbuzin.android.network.data.model.response.RegistrationResponse
import javax.inject.Inject

internal class Api @Inject constructor(
    clientProvider: NetworkClientProvider
) {
    private val httpClient = clientProvider.httpClient

    suspend fun authenticate(
        body: AuthRequest
    ) = httpClient.postData<AuthRequest, TokenPair>(
        path = AUTH_PATH,
        type = EndPointType.AUTH,
        requestBody = body
    )

    suspend fun registration(
        body: RegistrationRequest
    ) = httpClient.postData<RegistrationRequest, RegistrationResponse>(
        path = REGISTRATION_PATH,
        type = EndPointType.AUTH,
        requestBody = body
    )

    private companion object {
        const val AUTH_PATH = "/signin"
        const val REGISTRATION_PATH = "/registration"
    }
}