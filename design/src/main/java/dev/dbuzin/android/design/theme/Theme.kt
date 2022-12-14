package dev.dbuzin.android.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Colors.Background.lightYellow,
    primaryVariant = Colors.Background.yellow,
    secondary = Colors.Background.lightYellow,
    secondaryVariant = Colors.Background.yellow,
    background = Color.White,
    surface = Color.White,
    error = Colors.Element.error,
    onPrimary = Colors.Text.darkBrown,
    onSecondary = Colors.Text.darkBrown,
    onBackground = Colors.Text.darkBrown,
    onSurface = Colors.Text.darkBrown,
    onError = Color.White
)

@Composable
fun BeerReviewTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}