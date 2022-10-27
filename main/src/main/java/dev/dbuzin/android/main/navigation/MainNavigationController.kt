package dev.dbuzin.android.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.main.presentation.TabHostDestination

@Composable
fun MainNavigationController(
    systemUiController: SystemUiController
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainNavigationKeys.HOST,
        modifier = Modifier
    ) {
        composable(MainNavigationKeys.HOST) {
            TabHostDestination(
                systemUiController = systemUiController
            )
        }
    }
}