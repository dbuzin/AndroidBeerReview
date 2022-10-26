package dev.dbuzin.android.auth.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dbuzin.android.auth.domain.AuthInteractor
import dev.dbuzin.android.auth.domain.model.Account
import dev.dbuzin.android.core.presentation.BaseViewModel
import dev.dbuzin.android.core.presentation.ViewAction
import javax.inject.Inject

@HiltViewModel
internal class RegistrationViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    var state by mutableStateOf(RegistrationState.State())
        private set

    override fun onAction(action: ViewAction) {
        when (action) {
            is RegistrationState.Action.OnNameChanged -> {
                state = state.copy(
                    name = action.name
                )
                isSubmitEnabled()
            }
            is RegistrationState.Action.OnEmailChanged -> {
                state = state.copy(
                    email = action.email
                )
                isSubmitEnabled()
            }
            is RegistrationState.Action.OnPasswordChanged -> {
                state = state.copy(
                    password = action.password,
                    isPasswordError = action.password != state.passwordRepeat
                )
                isSubmitEnabled()
            }
            is RegistrationState.Action.OnRepeatChanged -> {
                state = state.copy(
                    passwordRepeat = action.repeat,
                    isPasswordError = state.password != action.repeat
                )
                isSubmitEnabled()
            }
            is RegistrationState.Action.OnNavigateBackClicked -> {
                sendEffect(RegistrationState.Effect.OnNavigateBack)
            }
            is RegistrationState.Action.OnSubmitRegistrationClicked -> launch(
                errorHandler = { exception ->
                    exception.printStackTrace()
                    state = state.copy(
                        isLoading = false
                    )
                    showError(exception)
                }
            ) {
                state = state.copy(
                    isLoading = true
                )
                val account = Account(
                    name = state.name,
                    email = state.email,
                    password = state.password
                )
                authInteractor.registration(account)
                state = state.copy(
                    isLoading = false
                )
                sendEffect(RegistrationState.Effect.OnRegistrationSuccess)
            }
        }
    }

    private fun isSubmitEnabled() {
        val isEnabled =  state.name.isNotEmpty()
                && state.email.isNotEmpty()
                && state.password.isNotEmpty()
                && (state.password == state.passwordRepeat)
        state = state.copy(
            isSubmitEnabled = isEnabled
        )
    }
}