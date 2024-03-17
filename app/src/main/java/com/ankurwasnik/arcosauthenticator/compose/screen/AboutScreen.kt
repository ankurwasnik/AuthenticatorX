package com.ankurwasnik.arcosauthenticator.compose.screen

import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankurwasnik.arcosauthenticator.ui.theme.ArcosAuthenticatorTheme
import com.ankurwasnik.arcosauthenticator.compose.component.support.SupportListItem
import com.ankurwasnik.arcosauthenticator.viewmodels.about.AboutViewModel


@Composable
fun AboutScreen(
    title :String = "About",
    modifier :Modifier = Modifier,
    theme : MaterialTheme = MaterialTheme,
) {

    val aboutItemList = AboutViewModel().AboutItemList
    ArcosAuthenticatorTheme {
        Surface {

            Column(
                modifier = modifier
                    .fillMaxSize()
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
                )
                {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxWidth(1f)
                            .padding(32.dp)
                    ) {
                        items(aboutItemList){
                            SupportListItem(
                                name = it.name,
                                desc = it.desc
                            ){

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
}


@Preview
@Composable
fun PreviewAboutScreen() {
    AboutScreen()
}