package dev.dbuzin.android.network.data.repository

import dev.dbuzin.android.network.cache.TokenProvider
import dev.dbuzin.android.network.client.Api
import dev.dbuzin.android.network.data.model.request.AuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject internal constructor(
    private val api: Api,
    private val tokenProvider: TokenProvider
) {
    suspend fun authenticate(body: AuthRequest) = withContext(Dispatchers.IO) {
        val tokens = api.authenticate(body)
        tokenProvider.setTokenPair(tokens)
    }
}