package dev.dbuzin.android.network.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class ErrorResponse(
    val message: String
)