package dev.dbuzin.android.core.error

fun Throwable.getUserFriendlyText(): String {
    return message ?: "Error"
}