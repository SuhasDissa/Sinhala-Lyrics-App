package app.suhasdissa.karoke.ui.screens.primary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.karoke.backend.viewmodels.SongState
import app.suhasdissa.karoke.backend.viewmodels.SongsViewModel
import app.suhasdissa.karoke.ui.components.LoadingScreen
import app.suhasdissa.karoke.ui.components.SongGrid

@Composable
fun SongsScreen(
    modifier: Modifier = Modifier,
    songViewModel: SongsViewModel = viewModel(factory = SongsViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    when (val songState = songViewModel.songState) {
        is SongState.Loading -> LoadingScreen(modifier)
        is SongState.Success -> SongGrid(
            songState.songs, modifier, onClickTextCard
        )
    }
}