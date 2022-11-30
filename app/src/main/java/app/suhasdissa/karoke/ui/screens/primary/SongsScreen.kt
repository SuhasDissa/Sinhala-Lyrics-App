package app.suhasdissa.karoke.ui.screens.primary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.karoke.backend.repositories.SongHeader
import app.suhasdissa.karoke.backend.viewmodels.SongState
import app.suhasdissa.karoke.backend.viewmodels.SongsViewModel
import app.suhasdissa.karoke.ui.components.LoadingScreen
import app.suhasdissa.karoke.ui.components.TextCard

@Composable
fun SongsScreen(
    modifier: Modifier = Modifier,
    songViewModel: SongsViewModel = viewModel(factory = SongsViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    when (val songState = songViewModel.songState) {
        is SongState.Loading -> LoadingScreen(modifier)
        is SongState.Success -> FeedGrid(
            songState.songs, modifier, onClickTextCard
        )
    }
}

@Composable
private fun FeedGrid(
    songs: ArrayList<SongHeader>,
    modifier: Modifier = Modifier,
    onClickTextCard: (url: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(500.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = songs) { item ->
            TextCard(
                clickAction = { onClickTextCard(item._id) },
                song = item.song,
                artist = item.artistName
            )
        }

    }
}