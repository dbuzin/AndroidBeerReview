package dev.dbuzin.android.home.presentation.menu

import dev.dbuzin.android.core.presentation.ViewAction
import dev.dbuzin.android.core.presentation.ViewEffect

internal object MenuState {

    sealed class Action: ViewAction {
        object OnReviewsClicked: Action()
        object OnBeerClicked: Action()
    }

    sealed class Effect: ViewEffect {
        object OnNavigateToReviews: Effect()
        object OnNavigateToBeer: Effect()
    }
}