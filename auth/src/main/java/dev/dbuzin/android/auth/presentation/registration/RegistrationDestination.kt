package dev.dbuzin.android.auth.presentation.registration

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun RegistrationDestination(
    onNavigateBack: () -> Unit
) {
    val viewModel: RegistrationViewModel = hiltViewModel()

    RegistrationScreen(
        viewState = viewModel.state,
        viewEffects = viewModel.viewEffects,
        onAction = viewModel::sendAction,
        onNavigateBack = onNavigateBack
    )
}