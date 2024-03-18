package com.ankurwasnik.arcosauthenticator.compose.component.support

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SupportListItem(
    name : String,
    desc :String,
    theme : MaterialTheme = MaterialTheme,
    modifier : Modifier = Modifier,
    onitemClick : ()->Unit,
) {
        Box(modifier = modifier.padding(top = 16.dp)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .border(1.dp, color = theme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable(
                        enabled = true,
                        onClick = onitemClick,
                        role = Role.Image
                    )

            ) {
                Text(
                    text = name,
                    style = theme.typography.bodyLarge,
                    maxLines = 1,
                    minLines = 1,
                    modifier = modifier
                        .weight(.3f)
                        .clickable(
                            enabled = true,
                            onClick = onitemClick,
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        )
                )
                Spacer(
                    modifier = modifier
                        .width(16.dp)
                        .border(1.dp, color = theme.colorScheme.primary)
                )
                Text(
                    text = desc,
                    style = theme.typography.bodyMedium,
                    minLines = 1,
                    modifier = modifier
                        .weight(.8f)
                        .fillMaxWidth()
                        .clickable(
                            enabled = true,
                            onClick = onitemClick,
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        )

                )

            }
        }

}


@Preview(showBackground = true)
@Composable
fun PreviewSupportListItem() {
    SupportListItem(
        name = "Support",
        desc = "Description",
        onitemClick = {}
    )
}