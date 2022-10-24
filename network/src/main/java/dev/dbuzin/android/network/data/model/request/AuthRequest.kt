package dev.dbuzin.android.network.data.model.request

import kotlinx.serialization.Serializable

@Serializable
class AuthRequest(
    val email: String,
    val password: String
)