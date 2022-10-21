package dev.dbuzin.android.network.client

import javax.inject.Inject

internal class Api @Inject constructor(
    private val clientProvider: NetworkClientProvider
) {
    private val httpClient = clientProvider.httpClient
}