package com.ankurwasnik.arcosauthenticator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankurwasnik.arcosauthenticator.ui.theme.ArcosAuthenticatorTheme
import com.ankurwasnik.arcosauthenticator.compose.screen.HomeScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.AboutScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.VerifyPassCodeScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.SettingScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.SupportScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.changePasscode.ConfirmNewPassCodeScreen
import com.ankurwasnik.arcosauthenticator.compose.screen.changePasscode.SetNewPassCodeScreen
import com.ankurwasnik.arcosauthenticator.utilities.ArcosSharedPreferenceManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArcosAuthenticatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArcosAuthenticatorApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArcosAuthenticatorApp() {
    // nav controller
    val navController = rememberNavController();
    val mContext = LocalContext.current
    val sharedPreferenceManager = ArcosSharedPreferenceManager(mContext)
    var passcode by remember {
        mutableStateOf(sharedPreferenceManager.getData("PASSCODE",""))
    }
    var isPassCodeSet  by remember {
        mutableStateOf(sharedPreferenceManager.getData("isPassCodeSet","false"))
    }
    var startDestination :String = "VerifyPasscode"
    if(isPassCodeSet == "false"){
        startDestination = "SetNewPasscode"
    }
    Box(Modifier.safeDrawingPadding()) {

        Log.i("START", "ArcosAuthenticatorApp: startdestination $startDestination")
        NavHost(navController = navController, startDestination = startDestination){

            composable("VerifyPasscode"){
                VerifyPassCodeScreen(
                    passcodeTitle = "Enter the passcode",
                ){
                    navController.navigate("GenerateOTP"){
                        popUpTo("VerifyPasscode")
                    }
                }
            }
            composable("SetNewPasscode"){
                SetNewPassCodeScreen(
                    sourceScreen = "SetNewPasscode",
                    passcodeTitle = "Set new passcode",
                    navController = navController
                )
            }
            composable("SetNewPasscode/{newPasscode}"){
                val newPasscode = it.arguments!!.getString("newPasscode")
                if (newPasscode != null) {
                    ConfirmNewPassCodeScreen(
                        newPasscode = newPasscode,
                        navController = navController,
                    )
                }
            }
            composable("Passcode"){
                VerifyPassCodeScreen(
                    navController = navController
                ){
                    navController.navigate("GenerateOTP"){
                        popUpTo("SetNewPasscode")
                    }
                }
            }
            composable("GenerateOTP"){
                HomeScreen(
                    onNavigateToSettings = {
                        navController.navigate("Settings")
                    }
                )
            }

            composable("Settings"){
                SettingScreen(
                    navController = navController,
                    onSupportClick = {
                        navController.navigate("Support")
                    },
                    onAboutClick = {
                        navController.navigate("About")
                    },
                    onChangePasswordClick = {
                        navController.navigate("SetNewPasscode")
                    }
                )
            }

            composable("Support"){
                SupportScreen()
            }

            composable("About"){
                AboutScreen()
            }
            
        }
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_5")
@Composable
fun previewMainActivity() {
    ArcosAuthenticatorApp()
}