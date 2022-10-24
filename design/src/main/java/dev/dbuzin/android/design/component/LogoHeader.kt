package dev.dbuzin.android.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import dev.dbuzin.android.design.R
import dev.dbuzin.android.design.theme.Colors

@Composable
fun LogoHeader(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.Center
) {
    Row(
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_text_logo),
            contentDescription = "text logo",
            tint = Colors.Text.aubergine,
            modifier = Modifier

        )
    }
}