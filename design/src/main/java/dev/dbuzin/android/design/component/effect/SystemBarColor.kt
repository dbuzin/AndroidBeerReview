package dev.dbuzin.android.design.component.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.design.theme.Colors

@Composable
@NonRestartableComposable
fun SystemBarColor(
    systemUiController: SystemUiController,
    specialNavColor: Color = Color.White,
    specialStatusColor: Color = Colors.Background.yellow
) {
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = specialNavColor,
            darkIcons = true,
        )
        systemUiController.setStatusBarColor(
            color = specialStatusColor,
            darkIcons = true
        )
    }
}