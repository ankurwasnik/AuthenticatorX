package com.ankurwasnik.arcosauthenticator.compose.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ankurwasnik.arcosauthenticator.ui.theme.ArcosAuthenticatorTheme
import com.ankurwasnik.arcosauthenticator.utilities.ArcosSharedPreferenceManager
import com.sharpedge.pintextfield.ComposePinInput


@SuppressLint("RestrictedApi")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyPassCodeScreen(
    title: String = "Arcos Authenticator",
    passcodeTitle: String = "Enter your passcode",
    modifier: Modifier = Modifier,
    theme: MaterialTheme = MaterialTheme,
    navController: NavController = rememberNavController(),
    navigateToOTPScreen : ()->Unit
) {
    val mContext = LocalContext.current
    val sharedPreferenceManager = ArcosSharedPreferenceManager(mContext)
    var passcode by remember{ mutableStateOf("") }

   ArcosAuthenticatorTheme {
       Surface {
           Column(
               modifier = modifier
                   .fillMaxSize(1f)
           ) {

               //Custom Top Bar
               Row(
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.Start,
                   modifier = modifier
                       .fillMaxWidth(1f)
                       .background(theme.colorScheme.primary)
                       .padding(8.dp)

               ) {
                   Text(
                       text = title,
                       textAlign = TextAlign.Center,
                       style = theme.typography.titleLarge,
                       modifier = modifier.fillMaxWidth(),
                       color = theme.colorScheme.onPrimary
                   )

               }
               Box(
                   contentAlignment = Alignment.Center,
                   modifier = Modifier.weight(0.8f)
               )
               {
                   LazyColumn(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center,
                       modifier = modifier
                           .fillMaxWidth(1f)
                           .padding(32.dp)
                   ) {
                       item{
                          Text(
                              text = passcodeTitle,
                              textAlign = TextAlign.Center,
                              fontSize = 24.sp,
                              style = theme.typography.titleLarge,
                              modifier = modifier
                                  .fillMaxWidth()
                                  .padding(16.dp)
                          )
                       }
                       item {
                           ComposePinInput(
                               value = passcode,
                               onValueChange = {
                                   passcode = it.trim()
                               },
                               onPinEntered = {
                                   passcode = it.trim()
                                   Log.i("PINENTERED", "PassCodeScreen: Pin entered $passcode")
                                   val isPasscodeVerified = isPassCodeVerified(
                                       passcode,
                                       sharedPreferenceManager = sharedPreferenceManager
                                   )
                                   if(isPasscodeVerified){
                                       navigateToOTPScreen()
                                   }
                                   else{
                                       Toast.makeText(mContext, "Incorrect passcode", Toast.LENGTH_SHORT).show()
                                   }
                               },
                               mask = '*',
                               cellSize = 40.dp,
                               cellShape = RoundedCornerShape(50.dp),
                               cellBorderColor = theme.colorScheme.onPrimaryContainer,
                               fontColor = theme.colorScheme.secondary,
                               focusedCellBorderColor = theme.colorScheme.secondary,
                           )
                       }

                   }
               }


               //spacer
               Spacer(modifier = modifier
                   .imePadding()
                   .weight(.2f))

           }
       }
   }
}

fun isPassCodeVerified(
    passcode :String,
    sharedPreferenceManager: ArcosSharedPreferenceManager
) :Boolean
{
    if(passcode.isNotEmpty()){
        val userPasscode = sharedPreferenceManager.getData("PASSCODE","")
        if(userPasscode.isNotEmpty() && passcode == userPasscode){
            return true
        }
    }
    return  false
}


@Preview
@Composable
fun PreviewPassCode() {
    val sharedPreferenceManager = ArcosSharedPreferenceManager(LocalContext.current)
    VerifyPassCodeScreen(navigateToOTPScreen = {})
}