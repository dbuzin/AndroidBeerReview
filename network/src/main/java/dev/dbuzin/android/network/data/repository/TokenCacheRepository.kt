package dev.dbuzin.android.network.data.repository

import dev.dbuzin.android.network.cache.TokenProvider
import javax.inject.Inject

class TokenCacheRepository @Inject internal constructor(
    private val tokenProvider: TokenProvider
) {
    fun hasCachedToken() = tokenProvider.hasCached()
}