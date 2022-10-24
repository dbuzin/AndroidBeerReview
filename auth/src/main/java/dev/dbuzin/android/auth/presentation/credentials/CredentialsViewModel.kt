package dev.dbuzin.android.auth.presentation.credentials

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dbuzin.android.auth.domain.AuthInteractor
import dev.dbuzin.android.auth.domain.model.Credentials
import dev.dbuzin.android.auth.domain.model.InvalidCredentials
import dev.dbuzin.android.core.presentation.BaseViewModel
import dev.dbuzin.android.core.presentation.ViewAction
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
internal class CredentialsViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    var state by mutableStateOf(CredentialsState.State())
        private set

    override fun onAction(action: ViewAction) {
        when(action) {
            is CredentialsState.Action.OnLoginChanged -> {
                state = state.copy(
                    login = action.login
                )
                state = state.copy(
                    isSubmitEnabled = isSubmitEnabled()
                )
            }
            is CredentialsState.Action.OnPasswordChanged -> {
                state = state.copy(
                    password = action.password
                )
                state = state.copy(
                    isSubmitEnabled = isSubmitEnabled()
                )
            }
            is CredentialsState.Action.OnSubmitCredentials -> launch(
                errorHandler = { exception ->
                    exception.printStackTrace()
                    onErrorCredentials(exception)
                }
            ) {
                state = state.copy(
                    isLoading = true
                )
                val result = authInteractor.authenticate(
                    credentials = Credentials(
                        email = state.login,
                        password = state.password
                    )
                )
                if (result) {
                    state = state.copy(
                        isLoading = false
                    )
                    sendEffect(CredentialsState.Effect.OnAuthSuccessful)
                } else {
                    onErrorCredentials()
                }
            }
            is CredentialsState.Action.OnRegistrationClicked -> {}
        }
    }

    private fun isSubmitEnabled(): Boolean {
        return EMAIL_ADDRESS.matcher(state.login).matches() && state.password.isNotEmpty()
    }

    private fun onErrorCredentials(throwable: Throwable = InvalidCredentials()) {
        launch {
            state = state.copy(
                isLoading = false
            )
            sendEffect(CredentialsState.Effect.OnAuthError)
            showError(throwable)
            state = state.copy(
                isError = true
            )
            delay(ERROR_DURATION)
            state = state.copy(
                isError = false
            )
        }
    }

    private companion object {
        const val ERROR_DURATION = 4000L
    }
}