package dev.dbuzin.android.splash.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dbuzin.android.core.presentation.BaseViewModel
import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.splash.domain.CheckCachedTokenUseCase
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    checkCachedUseCase: CheckCachedTokenUseCase
): BaseViewModel() {

    var state by mutableStateOf(SplashState.State())
        private set

    init {
        val isAuthorized = checkCachedUseCase()
        state = state.copy(
            isAuthorized = isAuthorized
        )
        sendAction(SplashState.Action.AuthCheckFinished)
    }

    override fun onAction(action: ViewAction) {
        when(action) {
            is SplashState.Action.AuthCheckFinished -> {
                if (state.isAuthorized) {
                    sendEffect(SplashState.Effect.OnNavigateToMain)
                } else {
                    sendEffect(SplashState.Effect.OnNavigateToAuth)
                }
            }
        }
    }
}