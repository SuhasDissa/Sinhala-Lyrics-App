package app.suhasdissa.lyrics.ui.screens.primary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.backend.viewmodels.SongsViewModel
import app.suhasdissa.lyrics.ui.components.SongGrid

@Composable
fun SongsScreen(
    modifier: Modifier = Modifier,
    songViewModel: SongsViewModel = viewModel(factory = SongsViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    if (songViewModel.songs.isNotEmpty()) {
        SongGrid(
            songViewModel.songs, modifier, onClickTextCard
        )
    }

}