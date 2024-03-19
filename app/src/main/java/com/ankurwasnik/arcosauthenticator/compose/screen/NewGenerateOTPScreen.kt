package com.ankurwasnik.arcosauthenticator.compose.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NewGenerateOTPScreen(
    modifier: Modifier = Modifier,
) {
    var drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Open)
    var scope: CoroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
            ) {
               ArcosModalDrawer()
            }
        }
    ) {
        Scaffold(
            topBar = {
                ArcosTopBar(
                    onNavMenuClicked = {
                       scope.launch {
                           drawerState.open()
                       }
                    }
                )
            },
        ) {
            Box(modifier = modifier
                .padding(it)
                .fillMaxSize()
            ){

            }
        }
    }

}
@Composable
fun NavDrawerItem(item: String, onClick: () -> Unit) =
    // making surface clickable causes to show the appropriate splash animation
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(50),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }


@Composable
fun ArcosModalDrawer(
    modifier: Modifier = Modifier,
    theme :MaterialTheme = MaterialTheme
) {
    val navDrawerList = arrayListOf<String>("hello","world")
    Surface(
        modifier = modifier.fillMaxWidth(.8f),
        color = theme.colorScheme.background
    ) {
       Column(
           modifier = modifier.padding(top = 8.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Row(
               modifier = modifier.fillMaxWidth()
           ){
                Text(
                    text = "AuthenticatorX",
                    style = theme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
           }
           Row(){
               Text(
                   text = "Welcome!",
                   style = theme.typography.bodyLarge,
                   textAlign = TextAlign.Center,
                   modifier = modifier.fillMaxWidth()
               )
           }

           Divider(
               modifier= modifier
                   .fillMaxWidth()
                   .padding(top = 4.dp)
                   .height(2.dp)
               ,
               color = theme.colorScheme.secondary
           )

           LazyColumn(){
                items(navDrawerList){
                    NavDrawerItem(item = it) {

                    }
                }
           }
           

       }
    }
}


@Preview
@Composable
fun PreviewNavDrawerItem() {
    NavDrawerItem(item = "hello") {
        
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArcosTopBar(
    title :String = "AuthenticatorX",
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onNavMenuClicked :()->Unit = {},
    modifier: Modifier = Modifier,
    theme :MaterialTheme = MaterialTheme
) {
    var isBackNavigationAllowed = !drawerState.equals(DrawerValue.Closed)
    TopAppBar(
        title = {
            Text(
                text = title,
                color = theme.colorScheme.onPrimary,
                textAlign = TextAlign.Start,
        ) },
        navigationIcon =
        {
            IconButton(onClick = onNavMenuClicked, colors = IconButtonDefaults.iconButtonColors()) {
                if(isBackNavigationAllowed){
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu",
                        tint = theme.colorScheme.onPrimary)
                    }
                else {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button",
                        tint = theme.colorScheme.onPrimary)
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(theme.colorScheme.primary)
    )
}

@Preview
@Composable
fun PreviewNewGenerateOTPScreen() {
    NewGenerateOTPScreen()
}