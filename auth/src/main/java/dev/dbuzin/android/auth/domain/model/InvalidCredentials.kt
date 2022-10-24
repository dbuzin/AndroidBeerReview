package dev.dbuzin.android.auth.domain.model

class InvalidCredentials(override val message: String? = "Invalid login or password!") : Throwable(message)