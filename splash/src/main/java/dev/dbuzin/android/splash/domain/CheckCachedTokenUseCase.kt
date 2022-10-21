package dev.dbuzin.android.splash.domain

import dev.dbuzin.android.network.data.repository.TokenCacheRepository
import javax.inject.Inject

internal class CheckCachedTokenUseCase @Inject constructor(
    private val tokenCacheRepository: TokenCacheRepository
) {
    operator fun invoke() = tokenCacheRepository.hasCachedToken()
}