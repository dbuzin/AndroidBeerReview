package dev.dbuzin.android.main.navigation.bottom

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.home.navigation.HomeNavigationController

@Composable
internal fun BottomNavigationController(
    navController: NavHostController,
    systemUiController: SystemUiController,
    onAppTabsVisibility: (Boolean) -> Unit,
    onNavItemChanged: (BottomNavigationKeys) -> Unit
) {
    BackHandler {
        val item = navController.previousBackStackEntry?.destination?.route
        if (item != null) {
            navController.popBackStack()
            onNavItemChanged(BottomNavigationKeys.getByRoute(item))
        } else {
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
            }.let { navController.context.startActivity(it) }
        }
    }

    NavHost(
        navController = navController,
        startDestination = BottomNavigationKeys.HOME.route,
        modifier = Modifier
    ) {
        composable(BottomNavigationKeys.HOME.route) {
            HomeNavigationController(
                onAppTabsVisibility = onAppTabsVisibility
            )
        }
        composable(BottomNavigationKeys.SCAN.route) {}
        composable(BottomNavigationKeys.PROFILE.route) {}
    }
}