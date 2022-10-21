package dev.dbuzin.android.splash.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun SplashDestination(
    systemUiController: SystemUiController,
    onNavigateToMain: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    val viewModel: SplashViewModel = hiltViewModel()

    SplashScreen(
        viewEffects = viewModel.viewEffects,
        systemUiController = systemUiController,
        onNavigateToMain = onNavigateToMain,
        onNavigateToAuth = onNavigateToAuth
    )
}