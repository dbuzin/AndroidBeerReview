package dev.dbuzin.android.design.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.design.R
import dev.dbuzin.android.design.theme.Colors
import dev.dbuzin.android.design.utils.rememberScreenSize

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val screenSize = rememberScreenSize()
    val iconSize = screenSize.screenWidth.times(0.05f).dp

    Box(
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
            contentDescription = "back",
            tint = Colors.Text.aubergine,
            modifier = Modifier
                .size(iconSize)
                .clickable(
                    role = Role.Button,
                    onClick = {
                        onClick()
                    }
                )
        )
    }
}