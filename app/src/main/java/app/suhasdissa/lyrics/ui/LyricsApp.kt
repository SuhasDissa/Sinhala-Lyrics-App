package app.suhasdissa.lyrics.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import app.suhasdissa.lyrics.*
import app.suhasdissa.lyrics.R
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun LyricsApp(modifier: Modifier = Modifier) {
    val navController = rememberAnimatedNavController()
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        var showMenu by remember { mutableStateOf(false) }
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = { navController.navigateTo(SearchView.route)}) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search"
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    painter = painterResource(R.drawable.ic_more_vert),
                    contentDescription = "More"
                )
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(text = {
                    Text(
                        stringResource(R.string.settings),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }, onClick = {
                    navController.navigateTo(Settings.route)
                    showMenu = false
                })
                DropdownMenuItem(text = {
                    Text(
                        stringResource(R.string.about),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }, onClick = {
                    navController.navigateTo(About.route)
                    showMenu = false
                })
            }

        })
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost(navController = navController, modifier = Modifier)
        }
    }
}