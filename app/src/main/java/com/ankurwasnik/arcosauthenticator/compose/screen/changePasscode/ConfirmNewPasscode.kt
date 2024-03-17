package com.ankurwasnik.arcosauthenticator.compose.screen.changePasscode

import android.annotation.SuppressLint
import android.content.Context
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


@Composable
fun ConfirmNewPassCodeScreen(
    newPasscode :String,
    navController: NavController,
    title: String = "Arcos Authenticator",
    passcodeTitle: String = "Confirm new passcode",
    sourceScreen: String? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    theme: MaterialTheme = MaterialTheme,
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
                                    Log.i("SETNEWPASSCODE", "Pin entered $passcode")
                                    setNewPasscode(
                                        newPasscode = newPasscode,
                                        currentPasscode = it.trim(),
                                        navController = navController,
                                        sharedPreferenceManager = sharedPreferenceManager,
                                        mContext = mContext
                                    )
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

fun setNewPasscode(
    newPasscode :String,
    currentPasscode :String,
    navController: NavController,
    sharedPreferenceManager: ArcosSharedPreferenceManager,
    mContext : Context
){
    if(newPasscode.isNotEmpty() &&
        currentPasscode.isNotEmpty() &&
        newPasscode.length == currentPasscode.length &&
        newPasscode == currentPasscode
    ){
        sharedPreferenceManager.saveData("PASSCODE",newPasscode)
        sharedPreferenceManager.saveData("isPassCodeSet","true")
        Toast.makeText(mContext, "Your passcode is set", Toast.LENGTH_SHORT).show()
        navController.navigate("GenerateOTP"){
            popUpTo("SetNewPasscode")
        }
    }
    else{
        Toast.makeText(mContext, "Incorrect Passcode", Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun PreviewConfirmNewPassCodeScreen() {
    val mContext = LocalContext.current
    ConfirmNewPassCodeScreen(
        "1234",
        navController = rememberNavController(),
    )
}