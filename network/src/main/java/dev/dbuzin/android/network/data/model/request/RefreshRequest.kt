package dev.dbuzin.android.network.data.model.request

import kotlinx.serialization.Serializable

@Serializable
class RefreshRequest(
    val refreshToken: String
)