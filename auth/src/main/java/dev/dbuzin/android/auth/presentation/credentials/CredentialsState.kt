package dev.dbuzin.android.auth.presentation.credentials

import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.core.presentation.ViewEffect

internal object CredentialsState {
    data class State(
        val login: String = "",
        val password: String = "",
        val isSubmitEnabled: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    )

    sealed class Action: ViewAction {
        class OnLoginChanged(val login: String): Action()
        class OnPasswordChanged(val password: String): Action()
        object OnSubmitCredentials: Action()
        object OnRegistrationClicked: Action()
    }

    sealed class Effect: ViewEffect {
        object OnAuthSuccessful: Effect()
        object OnAuthError: Effect()
        object OnNavigateToRegistration: Effect()
    }
}