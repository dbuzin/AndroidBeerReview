package dev.dbuzin.android.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.design.component.ErrorSnackBar
import dev.dbuzin.android.design.utils.rememberScreenSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    viewEffects: Flow<ViewEffect>,
    effectHandler: (ViewEffect, (ViewEffect) -> Unit) -> Unit = { effect, fallback ->
        fallback(effect)
    },
    content: @Composable () -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val screenSize = rememberScreenSize()
    val snackBarVerticalPadding = screenSize.screenHeight.times(0.3f)

    val effectsFallback = { effect: ViewEffect ->
        coroutineScope.launch {
            when (effect) {
                is SnackBarErrorEffect -> {
                    snackState.showSnackbar(
                        message = effect.errorMessage,
                        duration = SnackbarDuration.Short,
                    )
                }
                else -> {
                    throw IllegalArgumentException("Unknown effect type: ${effect.javaClass}")
                }
            }
        }
    }

    LaunchedEffect("view-effects") {
        viewEffects.onEach { effect -> effectHandler(effect, effectsFallback) }
            .launchIn(this)
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        content = {
            Box {
                ErrorSnackBar(
                    hostState = snackState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            horizontal = 16.dp,
                            vertical = snackBarVerticalPadding.dp
                        )
                )
                content()
            }
        },
    )
}