package dev.dbuzin.android.main.presentation

import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.main.navigation.bottom.BottomNavigationKeys

internal object TabHostState {
    data class State(
        val currentItem: BottomNavigationKeys = BottomNavigationKeys.HOME,
        val isBottomMenuVisible: Boolean = true
    )

    sealed class Action : ViewAction {
        class OnItemSelected(val item: BottomNavigationKeys) : Action()
        class OnBottomMenuVisibilityChanged(val isVisible: Boolean) : Action()
    }

    sealed class Effect : ViewEffect {}
}