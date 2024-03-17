package com.ankurwasnik.arcosauthenticator.compose.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ankurwasnik.arcosauthenticator.MainActivity
import com.ankurwasnik.arcosauthenticator.model.settings.SettingListItemModel
import com.ankurwasnik.arcosauthenticator.compose.component.settings.SettingListItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingScreen(
    subtitle: String = "Authenticator Settings",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    theme: MaterialTheme = MaterialTheme,
    navController: NavController = rememberNavController(),
    onSupportClick : ()->Unit = {},
    onAboutClick : ()-> Unit = {},
    onChangePasswordClick : ()->Unit = {}
) {

    val activity = LocalContext.current as MainActivity
    val settingItemList = listOf(
        SettingListItemModel(
            Icons.Outlined.Create,
            "Change Password",
            onItemClick = onChangePasswordClick
        ),
        SettingListItemModel(Icons.Outlined.Menu,"Generate OTP", onItemClick = {

            navController.navigate("GenerateOTP"){
                popUpTo("GenerateOTP"){
                    inclusive=true
                }
            }

        }),
        SettingListItemModel(Icons.Outlined.Build,"Support", onItemClick = onSupportClick),
        SettingListItemModel(Icons.Outlined.AccountCircle,"About", onItemClick = onAboutClick),
        SettingListItemModel(Icons.Outlined.AddCircle,"Create Instance"),
        SettingListItemModel(Icons.Outlined.Settings,"Manage Instance"),
        SettingListItemModel(Icons.Outlined.ExitToApp,"Exit", onItemClick = {
            activity.finish()
        }),
        )

        var enabled by remember { mutableStateOf(true) }
        val screenwidth:Float by animateFloatAsState(
            targetValue = if (enabled) 1f else 0.5f,
            animationSpec = tween(300, easing = FastOutSlowInEasing), label = ""
        )
        LazyColumn(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(screenwidth),
            horizontalAlignment = Alignment.Start,
        ){
            item {
                //Custom Top Bar
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .fillMaxWidth()
                        .background(theme.colorScheme.primary)

                ){

                    IconButton(onClick = {
                       navController.navigate("GenerateOTP"){
                           popUpTo("GenerateOTP"){
                               inclusive = false
                           }
                       }

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Menu",
                            tint = theme.colorScheme.onPrimary
                        )
                    }
                    Text(
                        text = subtitle,
                        textAlign = TextAlign.Center,
                        style = theme.typography.titleLarge,
                        modifier = modifier.fillMaxWidth(),
                        color = theme.colorScheme.onPrimary
                    )

                }
            }
            item{
                Spacer(modifier = modifier.height(16.dp))
            }
            items(settingItemList){item ->
                SettingListItem(
                    name = item.name,
                    prefixIcon = item.icon,
                    onitemClick = item.onItemClick
                )
            }
        }
 }



@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewSettingScreen() {
    SettingScreen()
}
