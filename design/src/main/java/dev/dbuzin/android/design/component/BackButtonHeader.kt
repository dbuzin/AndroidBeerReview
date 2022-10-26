package dev.dbuzin.android.design.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.dbuzin.android.design.component.button.BackButton

@Composable
fun BackButtonHeader(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateBack: () -> Unit
) {
    val buttonWeight = if (title.contains(" ")) 0.2f else 0.3f
    val textWeight = if (title.contains(" ")) 0.8f else 0.55f

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BackButton(
            modifier = Modifier
                .weight(buttonWeight)
        ) {
            onNavigateBack()
        }
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            modifier = Modifier
                .weight(textWeight)
        )
    }
}