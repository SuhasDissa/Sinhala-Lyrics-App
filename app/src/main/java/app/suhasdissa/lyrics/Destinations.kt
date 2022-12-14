package app.suhasdissa.lyrics

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object Home : Destination {
    override val route = "home"
}

object SearchView : Destination {
    override val route = "searchview"
}

object Settings : Destination {
    override val route = "settings"
}

object About : Destination {
    override val route = "about"
}

object AddSong : Destination {
    override val route = "addsong"
}
object EditSong : Destination {
    override val route = "editsong"
    val routeWithArgs = "$route/{SongID}"
    val arguments = listOf(navArgument("SongID") { type = NavType.IntType })
}

object SongViewer : Destination {
    override val route = "songviewer"
    val routeWithArgs = "$route/{SongID}"
    val arguments = listOf(navArgument("SongID") { type = NavType.IntType })
}

object ArtistFilter : Destination {
    override val route = "artistfilter"
    val routeWithArgs = "$route/{ArtistID}"
    val arguments = listOf(navArgument("ArtistID") { type = NavType.IntType })
}