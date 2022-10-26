package dev.dbuzin.android.auth.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.auth.R
import dev.dbuzin.android.core.presentation.ScreenContent
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.design.component.BackButtonHeader
import dev.dbuzin.android.design.component.LoadingView
import dev.dbuzin.android.design.component.SuccessSnackBar
import dev.dbuzin.android.design.component.button.PrimaryButton
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.ScreenSize
import dev.dbuzin.android.design.utils.rememberScreenSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun RegistrationScreen(
    viewState: RegistrationState.State,
    viewEffects: Flow<ViewEffect>,
    onAction: (RegistrationState.Action) -> Unit,
    onNavigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }
    val successMessage = stringResource(id = R.string.registration_success_title)

    val screenSize = rememberScreenSize()
    val contentPadding = when(screenSize.screenWidthInfo) {
        is ScreenSize.ScreenType.SMALL -> screenSize.screenHeight.times(0.3)
        is ScreenSize.ScreenType.MEDIUM -> screenSize.screenHeight.times(0.2)
        is ScreenSize.ScreenType.LARGE -> screenSize.screenHeight.times(0.1)
    }
    val snackBarVerticalPadding = screenSize.screenHeight.times(0.7f)

    val keyboardController = LocalSoftwareKeyboardController.current

    ScreenContent(
        viewEffects = viewEffects,
        effectHandler = { effect, fallback ->
            when(effect) {
                is RegistrationState.Effect.OnNavigateBack -> onNavigateBack()
                is RegistrationState.Effect.OnRegistrationSuccess -> coroutineScope.launch {
                    keyboardController?.hide()
                    snackState.showSnackbar(
                        message = successMessage,
                        duration = SnackbarDuration.Short
                    )
                    onNavigateBack()
                }
                else -> fallback(effect)
            }
        }
    ) {
        LoadingView(
            enabled = viewState.isLoading
        ) {
            Box {
                BackButtonHeader(
                    title = stringResource(id = R.string.registration_header_title),
                    onNavigateBack = {
                        onAction(RegistrationState.Action.OnNavigateBackClicked)
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(16.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = contentPadding.dp)
                ) {
                    RegistrationMainContent(
                        viewState = viewState,
                        onAction = onAction,
                        isSmallScreen = screenSize.screenHeightInfo is ScreenSize.ScreenType.SMALL
                    )
                }
                SuccessSnackBar(
                    hostState = snackState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp)
                        .padding(top = snackBarVerticalPadding.dp)
                )
            }
        }
    }
}

@Composable
private fun RegistrationMainContent(
    viewState: RegistrationState.State,
    onAction: (RegistrationState.Action) -> Unit,
    isSmallScreen: Boolean
) {
    val fieldsPadding = if (isSmallScreen) 4.dp else 8.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = viewState.name,
            onValueChange = { name ->
                onAction(RegistrationState.Action.OnNameChanged(name))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.registration_input_name)
                )
            },
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = fieldsPadding)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        OutlinedTextField(
            value = viewState.email,
            onValueChange = { email ->
                onAction(RegistrationState.Action.OnEmailChanged(email))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.registration_input_email)
                )
            },
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = fieldsPadding)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        OutlinedTextField(
            value = viewState.password,
            onValueChange = { password ->
                onAction(RegistrationState.Action.OnPasswordChanged(password))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.registration_input_password)
                )
            },
            isError = viewState.isPasswordError,
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = fieldsPadding)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        OutlinedTextField(
            value = viewState.passwordRepeat,
            onValueChange = { password ->
                onAction(RegistrationState.Action.OnRepeatChanged(password))
            },
            label = {
                Text(
                    text = stringResource(id = R.string.registration_input_repeat)
                )
            },
            isError = viewState.isPasswordError,
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = fieldsPadding)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        PrimaryButton(
            title = stringResource(id = R.string.registration_button_submit),
            onClick = {
                onAction(RegistrationState.Action.OnSubmitRegistrationClicked)
            },
            enabled = viewState.isSubmitEnabled,
            modifier = Modifier
                .padding(vertical = fieldsPadding)
        )
    }
}