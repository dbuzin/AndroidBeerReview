package dev.dbuzin.android.auth.domain

import dev.dbuzin.android.auth.domain.model.Account
import dev.dbuzin.android.auth.domain.model.Credentials
import dev.dbuzin.android.network.data.model.request.AuthRequest
import dev.dbuzin.android.network.data.model.request.RegistrationRequest

internal fun Credentials.toData() = AuthRequest(
    email = email,
    password = password
)

internal fun Account.toData() = RegistrationRequest(
    email = email,
    name = name,
    password = password
)