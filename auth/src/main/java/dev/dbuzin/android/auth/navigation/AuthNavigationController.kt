package dev.dbuzin.android.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.auth.presentation.credentials.CredentialsDestination

@Composable
fun AuthNavigationController(
    systemUiController: SystemUiController,
    onNavigateToMain: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthNavigationKeys.CREDENTIALS,
        modifier = Modifier
    ) {
        composable(AuthNavigationKeys.CREDENTIALS) {
            CredentialsDestination(
                systemUiController = systemUiController,
                onNavigateToMain = onNavigateToMain,
                onNavigateToRegistration = {}
            )
        }
    }
}