package dev.dbuzin.android.network.data.model.request

import kotlinx.serialization.Serializable

@Serializable
class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String
)