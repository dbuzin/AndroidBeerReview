package dev.dbuzin.android.auth.domain

import dev.dbuzin.android.auth.domain.model.Credentials
import dev.dbuzin.android.network.data.model.request.AuthRequest

internal fun Credentials.toData() = AuthRequest(
    email = email,
    password = password
)