package dev.dbuzin.android.home.presentation.menu

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MenuDestination(
    onNavigateToReviews: () -> Unit,
    onNavigateToBeer: () -> Unit
) {
    val viewModel: MenuViewModel = hiltViewModel()

    MenuScreen(
        onAction = viewModel::sendAction,
        viewEffects = viewModel.viewEffects,
        onNavigateToReviews = onNavigateToReviews,
        onNavigateToBeer = onNavigateToBeer
    )
}