package app.suhasdissa.lyrics.ui.screens.primary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.backend.repositories.data.SongUpdate
import app.suhasdissa.lyrics.backend.viewmodels.AddSongViewModel
import app.suhasdissa.lyrics.backend.viewmodels.LyricsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSongScreen(
    modifier: Modifier = Modifier,
    lyricId: Int? = null,
    lyricViewModel: LyricsViewModel = viewModel(factory = LyricsViewModel.Factory),
    addSongViewModel: AddSongViewModel = viewModel(factory = AddSongViewModel.Factory)
) {
    val song = lyricViewModel.song
    val songNameText = remember { mutableStateOf(TextFieldValue("")) }
    val artistNameText = remember { mutableStateOf(TextFieldValue("")) }
    val songLyricText = remember { mutableStateOf(TextFieldValue("")) }
    val songIDText = remember { mutableStateOf(TextFieldValue("0")) }
    val artistIDText = remember { mutableStateOf(TextFieldValue("0")) }
    LaunchedEffect(Unit) {
        if (lyricId != null) {
            lyricViewModel.getSong(lyricId)
            while(song.song.isEmpty()){}
            songNameText.value = TextFieldValue(song.song)
            artistNameText.value = TextFieldValue(song.artistName)
            songLyricText.value = TextFieldValue(song.lyric)
            songIDText.value = TextFieldValue(song._id.toString())
            artistIDText.value = TextFieldValue(song.artistID.toString())
        }
    }
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TextField(value = songNameText.value,
                onValueChange = {
                    songNameText.value = it
                },
                modifier.fillMaxWidth(),
                placeholder = { Text("Song Name") },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Song Name") })
        }
        item {
            TextField(value = artistNameText.value,
                onValueChange = {
                    artistNameText.value = it
                },
                modifier.fillMaxWidth(),
                placeholder = { Text("Singer's Name") },
                shape = CircleShape,
                label = { Text("Singer's Name") })
        }
        item {
            TextField(
                value = songIDText.value,
                onValueChange = {
                    songIDText.value = it
                },
                enabled = (lyricId != null),
                modifier = modifier.fillMaxWidth(),
                placeholder = { Text("0") },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Song ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        item {
            TextField(
                value = artistIDText.value,
                onValueChange = {
                    artistIDText.value = it
                },
                enabled = (lyricId != null),
                modifier = modifier.fillMaxWidth(),
                placeholder = { Text("0") },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Singer's ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        item {
            TextField(value = songLyricText.value,
                onValueChange = {
                    songLyricText.value = it
                },
                modifier.fillMaxWidth(),
                placeholder = { Text("Lyric") },
                shape = RoundedCornerShape(10.dp),
                label = { Text("Lyric") })
        }
        item {
            Row {
                val songName = songNameText.value.text
                val artistName = artistNameText.value.text
                val lyric = songLyricText.value.text
                Button(enabled = (lyric.isNotEmpty() && artistName.isNotEmpty() && songName.isNotEmpty()),
                    onClick = {
                        val id = songIDText.value.text.toInt()
                        val artistID = artistIDText.value.text.toInt()
                        addSongViewModel.addSong(
                            SongUpdate(
                                _id = id,
                                artistID = artistID,
                                song = songName,
                                artistName = artistName,
                                lyric = lyric
                            )
                        )
                    }) {
                    Text("Add Song")
                }
            }
        }
    }
}