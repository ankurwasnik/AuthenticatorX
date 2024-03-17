package com.ankurwasnik.arcosauthenticator.compose.component.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingListItem(
    name: String,
    prefixIcon : ImageVector?  = Icons.Outlined.Settings,
    prefixIconContentDesc :String? = null,
    theme :MaterialTheme = MaterialTheme,
    modifier :Modifier = Modifier,
    onitemClick : ()->Unit,
) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp, start = 16.dp)

        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .border(1.dp, color = theme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = onitemClick,
                        role = Role.Image
                    )
                    .padding(8.dp)
            ) {

                if(prefixIcon != null){
                    Icon(
                        prefixIcon,
                        contentDescription = prefixIconContentDesc,
                        tint = theme.colorScheme.primary,
                        modifier = modifier
                            .width(48.dp)
                            .height(48.dp)
                            .padding(horizontal = 8.dp)
                            .clickable(enabled = true, onClick = onitemClick, role = Role.Button)

                    )
                }
                else{
                    Spacer(modifier = modifier.width(64.dp))
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.padding(4.dp)
                ){
                    Text(
                        text = name,
                        style = theme.typography.bodyLarge,
                        maxLines = 1,
                        minLines = 1,
                        modifier = modifier
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
}


@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewSettingListItem() {
    SettingListItem(name = "Change Password", onitemClick = {})
}