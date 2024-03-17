package com.ankurwasnik.arcosauthenticator.compose.component.customOTPLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



enum class ComposePinInputStyle {
    BOX,
    UNDERLINE
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
public fun CustomOTPLayout(
    value: String,
    onValueChange: (String) -> Unit,
    maxSize: Int = 4,
    mask: Char? = null,
    isError: Boolean = false,
    onPinEntered: ((String) -> Unit)? = null,
    cellShape: Shape = RoundedCornerShape(4.dp),
    fontColor: Color = Color.LightGray,
    cellBorderColor: Color = Color.Gray,
    rowPadding: Dp = 8.dp,
    cellSize: Dp = 50.dp,
    cellBorderWidth: Dp = 1.dp,
    textFontSize: TextUnit = 20.sp,
    focusedCellBorderColor: Color = Color.DarkGray,
    errorBorderColor: Color = Color.Red,
    cellBackgroundColor: Color = Color.Transparent,
    cellColorOnSelect: Color = Color.Transparent,
    borderThickness: Dp = 2.dp,
    style: ComposePinInputStyle = ComposePinInputStyle.BOX,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = { text ->
                if (text.length <= maxSize) {
                    onValueChange(text)
                    if (text.length == maxSize) {
                        onPinEntered?.invoke(text)
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .alpha(0.3f),
            textStyle = TextStyle.Default.copy(color = Color.Transparent),
            enabled = false
            )

        // UI for the Pin
        val boxWidth = cellSize
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(rowPadding)
        ) {
            repeat(maxSize) { index ->
                if (style == ComposePinInputStyle.BOX) {
                    // Box Style Pin field logic starts from here
                    Box(
                        modifier = Modifier
                            .size(cellSize)
                            .background(
                                color = if (index < value.length) cellColorOnSelect else cellBackgroundColor,
                                shape = cellShape
                            )
                            .border(
                                width = cellBorderWidth,
                                color = when {
                                    isError -> errorBorderColor
                                    else -> cellBorderColor
                                },
                                shape = cellShape
                            )
                    ) {
                        if (index < value.length) {
                            val displayChar = mask ?: value[index]
                            Text(
                                text = displayChar.toString(),
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = textFontSize,
                                color = fontColor
                            )
                        }
                    }
                }
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewCustomOTPComponent() {
    CustomOTPLayout(value = "123456", onValueChange = {})
}
