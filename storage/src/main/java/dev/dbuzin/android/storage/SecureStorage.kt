package dev.dbuzin.android.storage

interface SecuredStorage {
    fun set(key: String, stringValue: String): Boolean

    fun exists(key: String): Boolean

    fun getString(key: String): String?

    fun clear(key: String): Boolean

    fun clearAll(): Boolean
}