package dev.dbuzin.android.auth.presentation.credentials

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
internal fun CredentialsDestination(
    systemUiController: SystemUiController,
    onNavigateToMain: () -> Unit,
    onNavigateToRegistration: () -> Unit
) {
    val viewModel: CredentialsViewModel = hiltViewModel()

    CredentialsScreen(
        viewState = viewModel.state,
        viewEffects = viewModel.viewEffects,
        onAction = viewModel::sendAction,
        systemUiController = systemUiController,
        onNavigateToMain = onNavigateToMain,
        onNavigateToRegistration = onNavigateToRegistration
    )
}