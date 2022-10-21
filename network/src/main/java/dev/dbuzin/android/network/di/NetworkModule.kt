package dev.dbuzin.android.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dbuzin.android.network.cache.TokenProvider
import dev.dbuzin.android.network.cache.TokenProviderImpl
import dev.dbuzin.android.network.client.Api
import dev.dbuzin.android.network.client.NetworkClientProvider
import dev.dbuzin.android.storage.SecuredStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideTokenProvider(
        tokenStorage: SecuredStorage
    ) : TokenProvider =
        TokenProviderImpl(
            tokenStorage = tokenStorage
        )

    @Provides
    @Singleton
    internal fun provideNetworkClientProvider(
        tokenProvider: TokenProvider
    ) = NetworkClientProvider(
        tokenProvider = tokenProvider
    )

    @Provides
    @Singleton
    internal fun provideApi(
        clientProvider: NetworkClientProvider
    ) = Api(
        clientProvider = clientProvider
    )
}