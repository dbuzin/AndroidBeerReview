package dev.dbuzin.android.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Colors.Text.aubergine,
    primaryVariant = Colors.Text.russet,
    secondary = Colors.Text.aubergine,
    secondaryVariant = Colors.Text.russet,
    background = Colors.Background.travertine,
    surface = Colors.Background.travertine,
    error = Colors.Element.error,
    onPrimary = Colors.Background.travertine,
    onSecondary = Colors.Background.travertine,
    onBackground = Colors.Text.aubergine,
    onSurface = Colors.Text.aubergine,
    onError = Colors.Background.travertine
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