package dev.dbuzin.android.auth.presentation.credentials

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import dev.dbuzin.android.auth.R
import dev.dbuzin.android.core.presentation.ScreenContent
import dev.dbuzin.android.core.presentation.ViewEffect
import dev.dbuzin.android.design.component.ErrorSnackBar
import dev.dbuzin.android.design.component.LoadingView
import dev.dbuzin.android.design.component.LogoHeader
import dev.dbuzin.android.design.component.button.PrimaryButton
import dev.dbuzin.android.design.component.effect.SystemBarColor
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.ScreenSize
import dev.dbuzin.android.design.utils.noRippleClickable
import dev.dbuzin.android.design.utils.rememberScreenSize
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun CredentialsScreen(
    viewState: CredentialsState.State,
    viewEffects: Flow<ViewEffect>,
    onAction: (CredentialsState.Action) -> Unit,
    systemUiController: SystemUiController,
    onNavigateToMain: () -> Unit,
    onNavigateToRegistration: () -> Unit
) {
    val screenSize = rememberScreenSize()
    val contentPadding = when(screenSize.screenHeightInfo) {
        is ScreenSize.ScreenType.SMALL -> screenSize.screenHeight.times(0.2)
        is ScreenSize.ScreenType.MEDIUM -> screenSize.screenHeight.times(0.1)
        is ScreenSize.ScreenType.LARGE -> screenSize.screenHeight.times(0.05)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    SystemBarColor(
        systemUiController = systemUiController,
        specialNavColor = Colors.Background.travertine,
        specialStatusColor = Colors.Background.travertine
    )
    ScreenContent(
        viewEffects = viewEffects,
        effectHandler = { effect, fallback ->
            when (effect) {
                is CredentialsState.Effect.OnAuthSuccessful -> onNavigateToMain()
                is CredentialsState.Effect.OnAuthError -> {
                    keyboardController?.hide()
                }
                is CredentialsState.Effect.OnNavigateToRegistration -> onNavigateToRegistration()
                else -> fallback(effect)
            }
        }
    ) {
        LoadingView(
            enabled = viewState.isLoading
        ) {
            Box {
                LogoHeader(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = contentPadding.dp)
                ) {
                    CredentialsScreenMainContent(
                        viewState = viewState,
                        onAction = onAction
                    )
                    CredentialsScreenRegistrationContent(
                        onAction = onAction
                    )
                }
            }
        }
    }
}

@Composable
private fun CredentialsScreenMainContent(
    viewState: CredentialsState.State,
    onAction: (CredentialsState.Action) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = viewState.login,
            onValueChange = { login ->
                onAction(CredentialsState.Action.OnLoginChanged(login))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Colors.Text.aubergine,
                backgroundColor = Color.White,
                focusedBorderColor = Colors.Text.aubergine,
                unfocusedBorderColor = Colors.Text.russet,
                cursorColor = Colors.Text.aubergine,
                focusedLabelColor = Colors.Text.aubergine,
                unfocusedLabelColor = Colors.Text.russet,
                errorBorderColor = Colors.Element.error,
                errorLabelColor = Colors.Element.error,
                errorCursorColor = Colors.Element.error,
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.credentials_input_login)
                )
            },
            isError = viewState.isError,
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        OutlinedTextField(
            value = viewState.password,
            onValueChange = { password ->
                onAction(CredentialsState.Action.OnPasswordChanged(password))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Colors.Text.aubergine,
                backgroundColor = Color.White,
                focusedBorderColor = Colors.Text.aubergine,
                unfocusedBorderColor = Colors.Text.russet,
                cursorColor = Colors.Text.aubergine,
                focusedLabelColor = Colors.Text.aubergine,
                unfocusedLabelColor = Colors.Text.russet,
                errorBorderColor = Colors.Element.error,
                errorLabelColor = Colors.Element.error,
                errorCursorColor = Colors.Element.error,
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.credentials_input_password)
                )
            },
            isError = viewState.isError,
            textStyle = MaterialTheme.typography.body2,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clip(
                    MaterialTheme.shapes.small
                )
        )

        PrimaryButton(
            title = stringResource(id = R.string.credentials_button_title),
            onClick = {
                onAction(CredentialsState.Action.OnSubmitCredentials)
            },
            enabled = viewState.isSubmitEnabled,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun CredentialsScreenRegistrationContent(
    onAction: (CredentialsState.Action) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.credentials_registration_text_p1),
            style = MaterialTheme.typography.caption,
            color = Colors.Text.russet
        )
        Text(
            text = stringResource(id = R.string.credentials_registration_text_p2),
            style = MaterialTheme.typography.caption,
            color = Colors.Text.russet,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .noRippleClickable {
                    onAction(CredentialsState.Action.OnRegistrationClicked)
                }
        )
    }
}