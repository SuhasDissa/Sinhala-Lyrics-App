package app.suhasdissa.karoke.ui.screens.secondary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.karoke.backend.viewmodels.LyricsViewModel

@Composable
fun SongView(
    lyricId: Int,
    modifier: Modifier = Modifier,
    lyricViewModel: LyricsViewModel = viewModel(factory = LyricsViewModel.Factory)
) {
    val song = lyricViewModel.song
    LaunchedEffect(Unit) {
        lyricViewModel.getSong(lyricId)
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 20.dp,horizontal = 10.dp), Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier.fillMaxWidth()
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(song.song, style = MaterialTheme.typography.headlineLarge)
                Text(song.artistName, style = MaterialTheme.typography.titleLarge)
            }
        }
        Card(
            modifier.fillMaxWidth()
        ) {
            LazyColumn(
                modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                item {
                    SelectionContainer(modifier.fillMaxWidth()) {
                        Text(song.lyric, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}