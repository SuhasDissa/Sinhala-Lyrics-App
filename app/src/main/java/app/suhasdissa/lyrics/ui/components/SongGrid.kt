package app.suhasdissa.lyrics.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.suhasdissa.lyrics.backend.repositories.data.SongHeader

@Composable
fun SongGrid(
    songs: ArrayList<SongHeader>, modifier: Modifier = Modifier, onClickTextCard: (url: Int) -> Unit
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