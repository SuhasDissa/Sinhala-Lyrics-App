package app.suhasdissa.lyrics.ui.screens.songreader.editsong

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.R
import app.suhasdissa.lyrics.backend.repositories.data.Artist
import app.suhasdissa.lyrics.backend.repositories.data.SongUpdate
import app.suhasdissa.lyrics.backend.viewmodels.AddSongViewModel
import app.suhasdissa.lyrics.backend.viewmodels.ArtistViewModel
import app.suhasdissa.lyrics.backend.viewmodels.LyricsViewModel
import app.suhasdissa.lyrics.ui.components.ArtistSelectDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSongScreen(
    modifier: Modifier = Modifier,
    lyricId: Int,
    lyricViewModel: LyricsViewModel = viewModel(factory = LyricsViewModel.Factory),
    addSongViewModel: AddSongViewModel = viewModel(factory = AddSongViewModel.Factory),
    artistViewModel: ArtistViewModel = viewModel(factory = ArtistViewModel.Factory)
) {
    LaunchedEffect(Unit) {
        lyricViewModel.getSong(lyricId)
    }
    val context = LocalContext.current
    val song = lyricViewModel.song
    val artistDialogOpen = remember { mutableStateOf(false) }
    val songNameText = remember { mutableStateOf(TextFieldValue(song.song)) }
    val songLyricText = remember { mutableStateOf(TextFieldValue(song.lyric)) }
    val songIDText = remember { mutableStateOf(TextFieldValue(song._id.toString())) }
    val selectedArtist = remember { mutableStateOf(Artist(0, "Select Singer")) }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.edit_lyric)) }, actions = {
            IconButton(onClick = {
                songNameText.value = TextFieldValue(song.song)
                songLyricText.value = TextFieldValue(song.lyric)
                songIDText.value = TextFieldValue(song._id.toString())
                val artist = artistViewModel.artists.firstOrNull { it.artistID == song.artistID }
                if (artist != null) {
                    selectedArtist.value = artist
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.AutoMode, contentDescription = "Refresh"
                )
            }
        })
    }) { innerPadding ->
        if (artistDialogOpen.value) {
            ArtistSelectDialog(
                optionsList = artistViewModel.artists,
                onSubmitButtonClick = {
                    selectedArtist.value = it
                },
                onDismissRequest = { artistDialogOpen.value = false },
                defaultSelectedArtist = selectedArtist.value
            )
        }
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                    placeholder = { Text(stringResource(R.string.song_name_text_field)) },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(stringResource(R.string.song_name_text_field)) })
            }
            item {
                ElevatedCard(
                    modifier
                        .clickable(onClick = { artistDialogOpen.value = true })
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            selectedArtist.value.artistName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            item {
                TextField(
                    value = songIDText.value,
                    onValueChange = {
                        songIDText.value = it
                    },
                    enabled = false,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text("0") },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Song ID") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item {
                TextField(value = songLyricText.value,
                    onValueChange = {
                        songLyricText.value = it
                    },
                    modifier.fillMaxWidth(),
                    placeholder = { Text(stringResource(R.string.lyric_text_field)) },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(stringResource(R.string.lyric_text_field)) })
            }
            item {
                Row {
                    val songName = songNameText.value.text
                    val artistName = selectedArtist.value.artistName
                    val artistID = selectedArtist.value.artistID
                    val lyric = songLyricText.value.text

                    Button(enabled = (lyric.isNotEmpty() && artistID != 0 && songName.isNotEmpty()),
                        onClick = {
                            val id = songIDText.value.text.toInt()
                            addSongViewModel.editSong(
                                SongUpdate(
                                    _id = id,
                                    artistID = artistID,
                                    song = songName,
                                    artistName = artistName,
                                    lyric = lyric
                                )
                            )
                            Toast.makeText(
                                context, "Song Updated Successfully", Toast.LENGTH_LONG
                            ).show()
                            songNameText.value = TextFieldValue("")
                            songLyricText.value = TextFieldValue("")
                            songIDText.value = TextFieldValue("0")
                        }) {
                        Text("Save Edits")
                    }
                }
            }
        }
    }
}