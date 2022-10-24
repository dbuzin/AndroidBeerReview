package dev.dbuzin.android.design.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.design.theme.Colors

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color = Colors.Text.aubergine,
    textColor: Color = Colors.Background.travertine,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = Colors.Text.russet
                )
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = textColor,
            disabledBackgroundColor = Colors.Text.russet,
            disabledContentColor = Colors.Background.lightYellow
        ),
        shape = MaterialTheme.shapes.small
    ) {
        if (icon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 12.dp)
            )
        }
        Text(
            text = title,
            color = textColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}