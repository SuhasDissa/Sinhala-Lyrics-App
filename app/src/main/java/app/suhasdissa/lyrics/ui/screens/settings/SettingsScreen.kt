package app.suhasdissa.lyrics.ui.screens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.lyrics.R
import app.suhasdissa.lyrics.backend.viewmodels.AddSongViewModel
import app.suhasdissa.lyrics.ui.components.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onAboutClick: () -> Unit,
    onClickAddSong: () -> Unit,
    addSongViewModel: AddSongViewModel = viewModel(factory = AddSongViewModel.Factory)
) {
    val context = LocalContext.current
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.settings_title)) })
    }) { innerPadding ->
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                SettingItem(
                    title = "Update Lyrics",
                    description = "Download the newly added lyrics from the internet",
                    onClick = {
                        addSongViewModel.updateSongs()
                        Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show()
                    },
                    icon = Icons.Outlined.Sync
                )
            }
            item {
                SettingItem(
                    title = "Add New Lyrics",
                    description = "Manually submit missing lyrics",
                    onClick = { onClickAddSong() },
                    icon = Icons.Outlined.PostAdd
                )
            }
            item {
                SettingItem(
                    title = "About",
                    description = "Developer Contact",
                    onClick = { onAboutClick() },
                    icon = Icons.Outlined.Info
                )
            }
        }
    }
}