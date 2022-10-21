package dev.dbuzin.android.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

internal class TokenStorage @Inject constructor(
    context: Context
) : SecuredStorage {
    private val encSharedPrefs: SharedPreferences

    init {
        val masterKey =
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        encSharedPrefs = EncryptedSharedPreferences.create(
            context,
            REVIEWS_APP_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun set(key: String, stringValue: String): Boolean {
        return encSharedPrefs
            .edit()
            .putString(key, stringValue)
            .commit()
    }

    override fun exists(key: String): Boolean {
        return encSharedPrefs.contains(key)
    }

    override fun getString(key: String): String? {
        return encSharedPrefs.getString(key, null)
    }

    override fun clear(key: String): Boolean {
        return encSharedPrefs
            .edit()
            .remove(key)
            .commit()
    }

    override fun clearAll(): Boolean {
        return encSharedPrefs
            .edit()
            .clear()
            .commit()
    }

    private companion object {
        private const val REVIEWS_APP_PREFS = "beer-reviews-encrypted-storage"
    }
}