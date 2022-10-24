package dev.dbuzin.android.design.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.dbuzin.android.design.R

@Composable
fun ErrorSnackBar(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
    @DrawableRes icon: Int = R.drawable.ic_warning
) {
    SnackbarHost(
        hostState = hostState,
        modifier = Modifier,
        snackbar = { data ->
            Card(
                shape = MaterialTheme.shapes.small,
                backgroundColor = Color.Black,
                modifier = modifier
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2,
                        color = Color.White,
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }
        }
    )
}