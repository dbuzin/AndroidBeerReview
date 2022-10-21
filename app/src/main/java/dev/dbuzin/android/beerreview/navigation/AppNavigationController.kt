package dev.dbuzin.android.beerreview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
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

                },
                onNavigateToMain = {

                }
            )
        }
    }
}