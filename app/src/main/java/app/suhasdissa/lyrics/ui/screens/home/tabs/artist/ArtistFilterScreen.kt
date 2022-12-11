package app.suhasdissa.lyrics.ui.screens.home.tabs.artist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.R
import app.suhasdissa.lyrics.backend.viewmodels.ArtistFilterViewModel
import app.suhasdissa.lyrics.backend.viewmodels.states.FilterState
import app.suhasdissa.lyrics.ui.components.MessageScreen
import app.suhasdissa.lyrics.ui.components.SongGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistFilterScreen(
    modifier: Modifier = Modifier,
    filterViewModel: ArtistFilterViewModel = viewModel(factory = ArtistFilterViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit,
    artistID: Int
) {
    val artist = filterViewModel.artist
    LaunchedEffect(Unit) {
        filterViewModel.getArtist(artistID)
        filterViewModel.filterArtist(artistID)
    }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(artist.artistName) }
        )
    }) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = filterViewModel.filterState) {
                is FilterState.Empty -> MessageScreen(R.string.no_songs_for_artist, modifier)
                is FilterState.Success -> if (state.songs.isEmpty()) {
                    MessageScreen(R.string.no_songs_for_artist, modifier)
                } else {
                    SongGrid(
                        state.songs, modifier, onClickTextCard
                    )
                }
            }
        }
    }
}