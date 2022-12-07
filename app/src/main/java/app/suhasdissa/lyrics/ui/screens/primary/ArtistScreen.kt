package app.suhasdissa.lyrics.ui.screens.primary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.backend.repositories.data.Artist
import app.suhasdissa.lyrics.backend.viewmodels.ArtistViewModel
import app.suhasdissa.lyrics.ui.components.ArtistCard

@Composable
fun ArtistScreen(
    modifier: Modifier = Modifier,
    artistViewModel: ArtistViewModel = viewModel(factory = ArtistViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    if (artistViewModel.artists.isNotEmpty()) {
        ArtistGrid(
            artistViewModel.artists, modifier, onClickTextCard
        )
    }
}

@Composable
private fun ArtistGrid(
    songs: ArrayList<Artist>, modifier: Modifier = Modifier, onClickTextCard: (url: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(500.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = songs) { item ->
            ArtistCard(
                clickAction = { onClickTextCard(item.artistID) }, artist = item.artistName
            )
        }

    }
}