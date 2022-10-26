package dev.dbuzin.android.auth.presentation.registration

import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.core.presentation.ViewEffect

internal object RegistrationState {
    data class State(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val passwordRepeat: String = "",
        val isPasswordError: Boolean = false,
        val isSubmitEnabled: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed class Action : ViewAction {
        class OnNameChanged(val name: String) : Action()
        class OnEmailChanged(val email: String) : Action()
        class OnPasswordChanged(val password: String) : Action()
        class OnRepeatChanged(val repeat: String) : Action()
        object OnNavigateBackClicked : Action()
        object OnSubmitRegistrationClicked: Action()
    }

    sealed class Effect : ViewEffect {
        object OnNavigateBack : Effect()
        object OnRegistrationSuccess : Effect()
    }
}