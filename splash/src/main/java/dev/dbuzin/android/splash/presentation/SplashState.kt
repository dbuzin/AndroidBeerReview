package dev.dbuzin.android.splash.presentation

import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.core.presentation.ViewEffect

internal object SplashState {

    data class State(
        val isAuthorized: Boolean = false
    )

    sealed class Action : ViewAction {
        object AuthCheckFinished: Action()
    }

    sealed class Effect : ViewEffect {
        object OnNavigateToMain: Effect()
        object OnNavigateToAuth: Effect()
    }
}