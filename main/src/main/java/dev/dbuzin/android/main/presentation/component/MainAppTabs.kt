package dev.dbuzin.android.main.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.noRippleClickable
import dev.dbuzin.android.main.R
import dev.dbuzin.android.main.navigation.bottom.BottomNavigationKeys

@Composable
internal fun MainAppTabs(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    onSelectTab: (BottomNavigationKeys) -> Unit
) {
    TabRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        backgroundColor = Colors.Background.sandal,
        selectedTabIndex = selectedTabIndex,
        indicator = {
            TabRowDefaults.Indicator(
                color = Color.Transparent
            )
        },
    ) {
        BottomNavigationKeys.values().forEachIndexed { index, item ->
            when(item) {
                BottomNavigationKeys.HOME -> {
                    BottomBarTab(
                        hostItem = BottomNavigationKeys.HOME,
                        title = stringResource(id = R.string.bottom_bar_item_home),
                        icon = dev.dbuzin.android.design.R.drawable.ic_home,
                        isSelected = index == selectedTabIndex,
                        onSelect = onSelectTab
                    )
                }
                BottomNavigationKeys.SCAN -> {
                    BottomBarTab(
                        hostItem = BottomNavigationKeys.SCAN,
                        title = stringResource(id = R.string.bottom_bar_item_scan),
                        icon = dev.dbuzin.android.design.R.drawable.ic_scanner,
                        isSelected = index == selectedTabIndex,
                        onSelect = onSelectTab
                    )
                }
                BottomNavigationKeys.PROFILE -> {
                    BottomBarTab(
                        hostItem = BottomNavigationKeys.PROFILE,
                        title = stringResource(id = R.string.bottom_bar_item_profile),
                        icon = dev.dbuzin.android.design.R.drawable.ic_account,
                        isSelected = index == selectedTabIndex,
                        onSelect = onSelectTab
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBarTab(
    hostItem: BottomNavigationKeys,
    title: String,
    @DrawableRes icon: Int,
    isSelected: Boolean,
    onSelect: (BottomNavigationKeys) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .noRippleClickable {
                onSelect(hostItem)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .background(
                    color = if (isSelected) Colors.Text.aubergine else Colors.Text.russet,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                tint = if (isSelected) Colors.Background.travertine else Colors.Text.aubergine,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            fontWeight = if (isSelected) FontWeight.W600 else FontWeight.Normal,
            color = if (isSelected) Colors.Background.travertine else Colors.Text.aubergine,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}