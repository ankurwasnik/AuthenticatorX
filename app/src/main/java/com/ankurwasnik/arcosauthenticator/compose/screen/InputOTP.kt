package com.ankurwasnik.arcosauthenticator.ui.view.component.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankurwasnik.arcosauthenticator.compose.component.customOTPLayout.CustomOTPLayout

@Composable
fun InputOTP(
    otp: String,
    length:Int = 4,
    modifier:Modifier = Modifier
) {
//    ComposePinInput(
//        value = otp,
//        maxSize = 6,
//        cellSize = 40.dp,
//        onValueChange = {
//
//        },
//        style = ComposePinInputStyle.BOX,
//        rowPadding = 0.dp,
//
//    )
    CustomOTPLayout(
        value = otp,
        maxSize = length,
        cellSize = 40.dp,
        onValueChange = {

        },
        rowPadding = 0.dp,
    )
}





@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewInputOTP() {
    InputOTP("123456")
}