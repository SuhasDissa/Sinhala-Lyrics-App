@file:Suppress("unused")

package app.suhasdissa.lyrics.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.suhasdissa.lyrics.backend.database.entities.Artist

@Composable
fun ArtistSelectDialog(
    modifier: Modifier = Modifier,
    optionsList: List<Artist>,
    defaultSelectedArtist: Artist,
    onSubmitButtonClick: (Artist) -> Unit,
    onDismissRequest: () -> Unit
) {
    val selectedArtist = remember { mutableStateOf(defaultSelectedArtist) }
    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Surface(
            modifier = modifier.width(300.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                Text("Select Singer")
                Spacer(modifier = modifier.height(10.dp))
                LazyColumn(modifier = modifier.height(500.dp)) {
                    items(items = optionsList) {
                        Row(
                            modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    selectedArtist.value = it
                                })
                                .padding(horizontal = 16.dp)
                        ) {
                            RadioButton(selected = (it.artistID == selectedArtist.value.artistID),
                                onClick = {
                                    selectedArtist.value = it
                                })
                            Text(
                                text = it.artistName, modifier = modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
                Button(onClick = {
                    onSubmitButtonClick(selectedArtist.value)
                    onDismissRequest.invoke()
                }) {
                    Text(text = "Select Singer")
                }
            }

        }
    }
}