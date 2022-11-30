package app.suhasdissa.karoke

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.suhasdissa.karoke.ui.screens.primary.*
import app.suhasdissa.karoke.ui.screens.secondary.SongView
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController, startDestination = Home.route, modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(onClickSongCard = { id ->
                navController.navigateTo("${SongViewer.route}/$id")
            }, onClickArtistCard = { })
        }
        composable(route = Settings.route) {
            SettingsScreen()
        }
        composable(route = About.route) {
            AboutScreen()
        }
        composable(route = SearchView.route) {
            SearchScreen(onClickTextCard = { id ->
                navController.navigateTo("${SongViewer.route}/$id")
            })
        }
        composable(
            route = SongViewer.routeWithArgs, arguments = SongViewer.arguments
        ) {
            val id = it.arguments?.getInt("SongID")
            if (id != null) {
                SongView(id)
            }
        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}