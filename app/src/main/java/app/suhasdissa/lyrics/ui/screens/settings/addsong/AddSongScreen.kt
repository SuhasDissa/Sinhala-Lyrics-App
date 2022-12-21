package app.suhasdissa.lyrics.ui.screens.settings.addsong

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import app.suhasdissa.lyrics.ui.components.ArtistSelectDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSongScreen(
    modifier: Modifier = Modifier,
    addSongViewModel: AddSongViewModel = viewModel(factory = AddSongViewModel.Factory),
    artistViewModel: ArtistViewModel = viewModel(factory = ArtistViewModel.Factory)
) {
    val context = LocalContext.current
    val artistDialogOpen = remember { mutableStateOf(false) }
    val songNameText = remember { mutableStateOf(TextFieldValue("")) }
    val songLyricText = remember { mutableStateOf(TextFieldValue("")) }
    val selectedArtist = remember { mutableStateOf(Artist(0, "Select Singer")) }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.add_lyric)) })
    }) { innerPadding ->
        if (artistDialogOpen.value) {
            ArtistSelectDialog(optionsList = artistViewModel.artists,
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
                    Row(modifier.fillMaxWidth().padding(10.dp)) {
                        Text(selectedArtist.value.artistName, style = MaterialTheme.typography.bodyLarge)
                    }
                }
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
                    val artistID = selectedArtist.value.artistID
                    val artistName = selectedArtist.value.artistName
                    val lyric = songLyricText.value.text

                    Button(enabled = (lyric.isNotEmpty() && artistID != 0 && songName.isNotEmpty()),
                        onClick = {
                            val id = 0
                            addSongViewModel.addSong(
                                SongUpdate(
                                    _id = id,
                                    artistID = artistID,
                                    song = songName,
                                    artistName = artistName,
                                    lyric = lyric
                                )
                            )
                            Toast.makeText(
                                context, "Song Added Successfully", Toast.LENGTH_LONG
                            ).show()
                            songNameText.value = TextFieldValue("")
                            songLyricText.value = TextFieldValue("")
                        }) {
                        Text("Add Song")
                    }

                }
            }
        }
    }
}