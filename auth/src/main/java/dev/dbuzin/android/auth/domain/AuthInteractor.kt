package dev.dbuzin.android.auth.domain

import dev.dbuzin.android.auth.domain.model.Credentials
import dev.dbuzin.android.network.data.repository.AuthRepository
import javax.inject.Inject

internal class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun authenticate(credentials: Credentials) =
        authRepository.authenticate(body = credentials.toData())
}