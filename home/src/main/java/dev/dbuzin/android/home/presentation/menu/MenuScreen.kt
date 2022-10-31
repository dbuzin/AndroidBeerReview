package dev.dbuzin.android.home.presentation.menu

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.core.presentation.ScreenContent
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.rememberScreenSize
import dev.dbuzin.android.home.R
import dev.dbuzin.android.home.presentation.menu.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Composable
internal fun MenuScreen(
    onAction: (MenuState.Action) -> Unit,
    viewEffects: Flow<ViewEffect>,
    onNavigateToReviews: () -> Unit,
    onNavigateToBeer: () -> Unit
) {
    ScreenContent(
        viewEffects = viewEffects,
        effectHandler = { effect: ViewEffect, fallback ->
            when(effect) {
                is MenuState.Effect.OnNavigateToReviews -> onNavigateToReviews()
                is MenuState.Effect.OnNavigateToBeer -> onNavigateToBeer()
                else -> fallback(effect)
            }
        }
    ) {
        MenuMainContent(
            onAction = onAction
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MenuMainContent(
    onAction: (MenuState.Action) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp),
        cells = GridCells.Fixed(2)
    ) {
        items(MenuItem.values()) { item ->
            when(item) {
                MenuItem.REVIEWS -> {
                    MenuItemCard(
                        title = stringResource(id = R.string.menu_item_reviews),
                        icon = dev.dbuzin.android.design.R.drawable.reviews_menu,
                        onClick = {
                            onAction(MenuState.Action.OnReviewsClicked)
                        }
                    )
                }
                MenuItem.BEER -> {
                    MenuItemCard(
                        title = stringResource(id = R.string.menu_item_beer),
                        icon = dev.dbuzin.android.design.R.drawable.beer_menu,
                        onClick = {
                            onAction(MenuState.Action.OnBeerClicked)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuItemCard(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    val screenSize = rememberScreenSize()
    val cardSize = (screenSize.screenWidth - (24 * 3)) / 2

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(cardSize.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(
                color = Colors.Background.sandal,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(
                role = Role.Tab
            ) {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(cardSize.div(3.0).dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.W600,
            color = Colors.Text.aubergine,
            modifier = Modifier
                .padding(top = 12.dp)
        )
    }
}