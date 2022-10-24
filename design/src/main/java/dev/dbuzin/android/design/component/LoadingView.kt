package dev.dbuzin.android.design.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.dbuzin.android.design.R
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.gesturesDisabled
import dev.dbuzin.android.design.utils.rememberScreenSize

@Composable
fun LoadingView(
    enabled: Boolean,
    content: @Composable () -> Unit
) {
    val screenSize = rememberScreenSize()
    val animDuration = 1500
    var progressIndicatorAlphaState by remember { mutableStateOf(0f) }
    val progressIndicatorAlphaAnim by animateFloatAsState(targetValue = progressIndicatorAlphaState, tween(animDuration))

    var boxAlphaState by remember { mutableStateOf(1f) }
    val boxAlphaAnim by animateFloatAsState(targetValue = boxAlphaState, tween(animDuration))

    val indicatorSize = screenSize.screenHeight.div(3).dp

    if (enabled) {
        SideEffect {
            progressIndicatorAlphaState = 1f
            boxAlphaState = 0.4f
        }
    } else {
        SideEffect {
            progressIndicatorAlphaState = 0f
            boxAlphaState = 1.0f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .gesturesDisabled(enabled)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Colors.Background.travertine)
                .alpha(boxAlphaAnim)
        ) {
            content()
        }
        LottieBeerProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(indicatorSize)
                .alpha(progressIndicatorAlphaAnim),
            enabled = enabled
        )
    }
}

@Composable
private fun LottieBeerProgressIndicator(
    modifier: Modifier,
    enabled: Boolean
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.beer_loading_animation))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = enabled,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}