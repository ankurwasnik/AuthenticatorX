package com.ankurwasnik.arcosauthenticator.compose.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.ankurwasnik.arcosauthenticator.MainActivity
import com.ankurwasnik.arcosauthenticator.ui.view.component.screen.InputOTP

enum class OTPGeneratingStates{
    NOTGENERATING,
    GENERATING,
    GENERATED
}

enum class CHALLENGESTATE{
    NULL,
    TYPING,
    COMPLETED,
    OVERFLOW
}

enum class SCREEN{
    HOMESCREEN,
    SETTING
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    title :String = "Arcos Authenticator",
    theme :MaterialTheme = MaterialTheme,
    modifier :Modifier = Modifier,
    onNavigateToSettings :()->Unit,
) {
    val mContext = LocalContext.current as MainActivity


    var instanceName by remember{ mutableStateOf("") }
    var challenge by remember{ mutableStateOf("") }
    var otp by remember {
        mutableStateOf("")
    }
    var challengeState by remember { mutableStateOf(CHALLENGESTATE.NULL) }
    var isOTPGenerating by remember{ mutableStateOf(OTPGeneratingStates.NOTGENERATING)}
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val (focusRequester) = FocusRequester.createRefs()
    BackHandler {
        mContext.finish()
    }
    var isFocusNeeded by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit){
        if(isFocusNeeded) focusRequester.requestFocus()
        else {
            focusRequester.freeFocus()
        }
    }

    Surface{

            Column(
                modifier = modifier
                    .fillMaxSize(1f)
                    .background(theme.colorScheme.surface)
            ){
                //Custom Top Bar
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .fillMaxWidth(1f)
                        .background(theme.colorScheme.primary)

                ){

                    IconButton(onClick = {
                        isFocusNeeded = false
                        onNavigateToSettings()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = theme.colorScheme.onPrimary
                        )
                    }
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
                        modifier = modifier
                            .fillMaxWidth(1f)
                            .padding(32.dp)
                    ) {
                        item{

                            OutlinedTextField(
                                value = instanceName,
                                onValueChange = {
                                    instanceName = it.trim()
                                },
                                label = { Text(text = "Instance Name")},
                                placeholder = { Text(text = "Enter instance name")},
                                singleLine = true,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester)
                                    ,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                            )
                        }
                        item{
                            OutlinedTextField(
                                value = challenge,
                                onValueChange = {
                                    if(it.trim().length > 6){
                                        challenge = it.substring(0,6)
                                    }
                                    else{
                                        challenge = it.trim()
                                        challengeState = validateChallenge(challenge,challengeState)
                                    }
                                },
                                label = { Text(text = "Challenge")},
                                placeholder = { Text(text = "Enter challenge")},
                                modifier = modifier.fillMaxWidth(1f),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.NumberPassword,
                                    imeAction = ImeAction.Done
                                ),
                                isError = challengeState == CHALLENGESTATE.OVERFLOW,
                                singleLine = true,
                                supportingText = {
                                    if(challengeState == CHALLENGESTATE.TYPING){
                                        Text("Typing...")
                                    }
                                },
                                keyboardActions = KeyboardActions(
                                    onDone  = {
                                        if(challenge.isNotEmpty() && challenge.length == 6){
                                            keyboardController?.hide()
                                        }
                                        if(challenge.isNullOrEmpty()){
                                            keyboardController?.hide()
                                        }
                                    }
                                )


                            )
                        }

                        item{
                            if(isOTPGenerating == OTPGeneratingStates.GENERATED){
                                Spacer(modifier = modifier.height(16.dp))
                                InputOTP( otp, length = 6)
                            }
                        }

                        item{
                            Button(
                                onClick = {
                                    if (instanceName.isNotEmpty() && challenge.isNotEmpty() && challenge.length == 6){
                                        Toast.makeText(context, "OTP Generated", Toast.LENGTH_SHORT).show()
                                        isOTPGenerating = OTPGeneratingStates.GENERATED
                                        challenge = ""
                                        otp = generateOTP()
                                    }
                                },
                                modifier = modifier
                                    .padding(vertical = 16.dp)
                                    .fillMaxWidth(1f),
                                enabled = challenge.isNotEmpty() && challenge.length == 6 && instanceName.isNotEmpty()
                            ) {
                                Text(text = "Generate OTP")
                            }
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

fun generateOTP(length: Int = 6) :String {
    var otp :Int = 0;
    for (i in (1..length)){
        otp += otp*10 + (0..9).random()
    }
    return otp.toString()
}

fun validateChallenge(challenge :String, challengeState : CHALLENGESTATE)
: CHALLENGESTATE
{
    var tempState = challengeState
    if(challenge.isNullOrBlank() || challenge.isNullOrEmpty()){
        tempState = CHALLENGESTATE.NULL
    }
    if(challenge.isNotEmpty() && challenge.isNotBlank()){
        if(challenge.length < 6){
            tempState = CHALLENGESTATE.TYPING
        }
    }
    if(challenge.isNotEmpty() && challenge.isDigitsOnly() && challenge.length == 6)
    {
        tempState = CHALLENGESTATE.COMPLETED
    }

    if(challenge.isNotEmpty() && challenge.isNotBlank() && challenge.length > 6){
        tempState = CHALLENGESTATE.OVERFLOW
    }
    return tempState

}


 fun getNewScreenToNavigate(currentSCREEN: SCREEN) : SCREEN {
     var newScreen : SCREEN = currentSCREEN
    if(newScreen == SCREEN.SETTING){
        newScreen = SCREEN.HOMESCREEN
    }
     else {
        newScreen = SCREEN.SETTING
    }
     return newScreen
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(onNavigateToSettings = {})
}