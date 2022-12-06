package app.suhasdissa.lyrics.ui.screens.primary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.backend.viewmodels.ArtistFilterViewModel
import app.suhasdissa.lyrics.backend.viewmodels.FilterState
import app.suhasdissa.lyrics.ui.components.LoadingScreen
import app.suhasdissa.lyrics.ui.components.MessageScreen
import app.suhasdissa.lyrics.ui.components.SongGrid

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
    Column(modifier.fillMaxSize()) {
        Card(
            modifier.fillMaxWidth()
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(artist.artistName, style = MaterialTheme.typography.titleLarge)
            }
        }
        when (val state = filterViewModel.filterState) {
            is FilterState.Empty -> MessageScreen(R.string.no_songs_for_artist, modifier)
            is FilterState.Loading -> LoadingScreen(modifier)
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