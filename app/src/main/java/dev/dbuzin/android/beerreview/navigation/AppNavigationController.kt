package dev.dbuzin.android.beerreview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.auth.navigation.AuthNavigationController
import dev.dbuzin.android.main.navigation.MainNavigationController
import dev.dbuzin.android.splash.presentation.SplashDestination

@Composable
internal fun AppNavigationController(
    systemUiController: SystemUiController
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppNavigationKeys.SPLASH,
        modifier = Modifier
    ) {
        composable(AppNavigationKeys.SPLASH) {
            SplashDestination(
                systemUiController = systemUiController,
                onNavigateToAuth = {
                    navController.navigate(AppNavigationKeys.AUTH) {
                        popUpTo(AppNavigationKeys.SPLASH) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(AppNavigationKeys.MAIN) {
                        popUpTo(AppNavigationKeys.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(AppNavigationKeys.AUTH) {
            AuthNavigationController(
                systemUiController = systemUiController,
                onNavigateToMain = {
                    navController.navigate(AppNavigationKeys.MAIN) {
                        popUpTo(AppNavigationKeys.AUTH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(AppNavigationKeys.MAIN) {
            MainNavigationController(
                systemUiController = systemUiController
            )
        }
    }
}