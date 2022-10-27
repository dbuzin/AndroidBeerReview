package dev.dbuzin.android.main.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dbuzin.android.core.presentation.BaseViewModel
import dev.dbuzin.android.core.presentation.ViewAction
import javax.inject.Inject

@HiltViewModel
internal class TabHostViewModel @Inject constructor() : BaseViewModel() {

    var state by mutableStateOf(TabHostState.State())

    override fun onAction(action: ViewAction) {
        when(action) {
            is TabHostState.Action.OnItemSelected -> {
                state = state.copy(
                    currentItem = action.item
                )
            }
            is TabHostState.Action.OnBottomMenuVisibilityChanged -> {
                state = state.copy(
                    isBottomMenuVisible = action.isVisible
                )
            }
        }
    }
}