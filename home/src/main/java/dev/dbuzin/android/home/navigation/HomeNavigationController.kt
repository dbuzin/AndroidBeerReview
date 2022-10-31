package dev.dbuzin.android.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dbuzin.android.home.presentation.menu.MenuDestination

@Composable
fun HomeNavigationController(
    onAppTabsVisibility: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNavigationKeys.MENU,

    ) {
        composable(HomeNavigationKeys.MENU) {
            MenuDestination(
                onNavigateToReviews = {
                    navController.navigate(HomeNavigationKeys.REVIEWS)
                },
                onNavigateToBeer = {
                    navController.navigate(HomeNavigationKeys.BEER)
                }
            )
        }
        composable(HomeNavigationKeys.REVIEWS) {}
        composable(HomeNavigationKeys.BEER) {}
    }
}