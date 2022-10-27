package dev.dbuzin.android.main.presentation

import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.core.presentation.ScreenContent
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.design.component.effect.SystemBarColor
import dev.dbuzin.android.design.utils.rememberScreenSize
import dev.dbuzin.android.main.navigation.bottom.BottomNavigationController
import dev.dbuzin.android.main.navigation.bottom.BottomNavigationKeys
import dev.dbuzin.android.main.presentation.component.MainAppTabs
import kotlinx.coroutines.flow.Flow

@Composable
internal fun TabHostScreen(
    viewState: TabHostState.State,
    onAction: (TabHostState.Action) -> Unit,
    viewEffects: Flow<ViewEffect>,
    systemUiController: SystemUiController,
) {
    val screenSize = rememberScreenSize()

    val navController = rememberNavController()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val tabsOffset = screenSize.screenHeight.times(0.3f)

    SystemBarColor(
        systemUiController = systemUiController
    )

    ScreenContent(
        viewEffects = viewEffects,
        effectHandler = { effect: ViewEffect, fallback ->
            when (effect) {
                else -> fallback(effect)
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                MainAppTabs(
                    selectedTabIndex = BottomNavigationKeys.values()
                        .indexOf(viewState.currentItem),
                    onSelectTab = { item ->
                        onAction(TabHostState.Action.OnItemSelected(item))
                    },
                    modifier = Modifier
                        .offset(y = if (viewState.isBottomMenuVisible) 0.dp else tabsOffset.dp)
                )
            },
            scaffoldState = scaffoldState
        ) {
            BottomNavigationController(
                navController = navController,
                systemUiController = systemUiController,
                onAppTabsVisibility = { visible ->
                    onAction(TabHostState.Action.OnBottomMenuVisibilityChanged(visible))
                },
                onNavItemChanged = { item ->
                    onAction(TabHostState.Action.OnItemSelected(item))
                }
            )
            when (viewState.currentItem) {
                BottomNavigationKeys.HOME -> {
                    if (navController.currentDestination?.route != BottomNavigationKeys.HOME.route) {
                        navController.navigate(BottomNavigationKeys.HOME.route)
                    }
                }
                BottomNavigationKeys.SCAN -> {
                    if (navController.currentDestination?.route != BottomNavigationKeys.SCAN.route) {
                        navController.navigate(BottomNavigationKeys.SCAN.route)
                    }
                }
                BottomNavigationKeys.PROFILE -> {
                    if (navController.currentDestination?.route != BottomNavigationKeys.PROFILE.route) {
                        navController.navigate(BottomNavigationKeys.PROFILE.route)
                    }
                }
            }
        }
    }
}