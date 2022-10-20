package dev.dbuzin.android.core.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
    content: @Composable (PaddingValues) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val effectsFallback = { effect: ViewEffect ->
        coroutineScope.launch {
            when (effect) {
                is SnackBarErrorEffect -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = effect.errorMessage,
                        duration = SnackbarDuration.Long,
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
        content = content,
    )
}