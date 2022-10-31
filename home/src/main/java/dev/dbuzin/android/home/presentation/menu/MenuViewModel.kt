package dev.dbuzin.android.home.presentation.menu

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dbuzin.android.core.presentation.BaseViewModel
import dev.dbuzin.android.core.presentation.ViewAction
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : BaseViewModel() {

    override fun onAction(action: ViewAction) {
        when(action) {
            MenuState.Action.OnReviewsClicked -> {
                sendEffect(MenuState.Effect.OnNavigateToReviews)
            }
            MenuState.Action.OnBeerClicked -> {
                sendEffect(MenuState.Effect.OnNavigateToBeer)
            }
        }
    }
}