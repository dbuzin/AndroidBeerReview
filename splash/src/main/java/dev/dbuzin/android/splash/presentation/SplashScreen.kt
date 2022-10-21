package dev.dbuzin.android.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.core.presentation.ScreenContent
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.design.R
import dev.dbuzin.android.design.component.effect.SystemBarColor
import dev.dbuzin.android.design.theme.Colors
import kotlinx.coroutines.flow.Flow

@Composable
internal fun SplashScreen(
    viewEffects: Flow<ViewEffect>,
    systemUiController: SystemUiController,
    onNavigateToMain: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    SystemBarColor(
        systemUiController = systemUiController,
        specialStatusColor = Color.White,
        specialNavColor = Colors.Background.lightYellow
    )

    ScreenContent(
        viewEffects = viewEffects,
        effectHandler = { effect: ViewEffect, fallback ->
            when(effect) {
                SplashState.Effect.OnNavigateToMain -> onNavigateToMain()
                SplashState.Effect.OnNavigateToAuth -> onNavigateToAuth()
                else -> fallback(effect)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Colors.Background.lightYellow)
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "splash",
                alignment = Alignment.Center,
                modifier = Modifier
            )
        }
    }
}