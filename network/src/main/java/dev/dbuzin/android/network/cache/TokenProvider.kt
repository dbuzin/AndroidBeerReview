package dev.dbuzin.android.network.cache

import dev.dbuzin.android.network.data.model.common.TokenPair
import dev.dbuzin.android.storage.SecuredStorage
import javax.inject.Inject

internal interface TokenProvider {
    fun setTokenPair(tokens: TokenPair)
    fun getTokenPair(): TokenPair
    fun clear(): Boolean
}

internal class TokenProviderImpl @Inject constructor(
    private val tokenStorage: SecuredStorage
) : TokenProvider {
    override fun setTokenPair(tokens: TokenPair) {
        tokenStorage.set(
            key = KEY_ACCESS,
            stringValue = tokens.accessToken
        )
        tokenStorage.set(
            key = KEY_REFRESH,
            stringValue = tokens.refreshToken
        )
    }

    override fun getTokenPair(): TokenPair {
        val access = tokenStorage.getString(KEY_ACCESS).orEmpty()
        val refresh = tokenStorage.getString(KEY_REFRESH).orEmpty()
        return TokenPair(
            accessToken = access,
            refreshToken = refresh
        )
    }

    override fun clear(): Boolean {
        return tokenStorage.clearAll()
    }

    private companion object {
        const val KEY_ACCESS = "ACCESS_TOKEN"
        const val KEY_REFRESH = "REFRESH_TOKEN"
    }
}