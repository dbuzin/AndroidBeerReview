package dev.dbuzin.android.main.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
internal fun TabHostDestination(
    systemUiController: SystemUiController
) {
    val viewModel: TabHostViewModel = hiltViewModel()

    TabHostScreen(
        viewState = viewModel.state,
        onAction = viewModel::sendAction,
        viewEffects = viewModel.viewEffects,
        systemUiController = systemUiController
    )
}