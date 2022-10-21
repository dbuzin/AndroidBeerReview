package dev.dbuzin.android.design.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberScreenSize(): ScreenSize {
    val config = LocalConfiguration.current
    return ScreenSize(
        screenWidthInfo = when {
            config.screenWidthDp < 600 -> ScreenSize.ScreenType.SMALL
            config.screenWidthDp < 840 -> ScreenSize.ScreenType.MEDIUM
            else -> ScreenSize.ScreenType.LARGE
        },
        screenHeightInfo = when {
            config.screenHeightDp < 480 -> ScreenSize.ScreenType.SMALL
            config.screenHeightDp < 900 -> ScreenSize.ScreenType.MEDIUM
            else -> ScreenSize.ScreenType.LARGE
        },
        screenWidth = config.screenWidthDp,
        screenHeight = config.screenHeightDp
    )
}

data class ScreenSize(
    val screenWidthInfo: ScreenType,
    val screenHeightInfo: ScreenType,
    val screenWidth: Int,
    val screenHeight: Int
) {
    sealed class ScreenType {
        object SMALL: ScreenType()
        object MEDIUM: ScreenType()
        object LARGE: ScreenType()
    }
}