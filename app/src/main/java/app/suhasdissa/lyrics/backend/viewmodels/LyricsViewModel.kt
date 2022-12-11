package app.suhasdissa.lyrics.backend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.lyrics.LyricsApplication
import app.suhasdissa.lyrics.backend.repositories.SongRepository
import app.suhasdissa.lyrics.backend.repositories.data.Song
import kotlinx.coroutines.launch

class LyricsViewModel(private val songRepository: SongRepository) : ViewModel() {
    var song: Song by mutableStateOf(
        Song(
            artistID = 0,
            song = "",
            lyric = "",
            artistName = "",
            _id = 0
        )
    )
        private set


    fun getSong(id: Int) {
        viewModelScope.launch {
            song = songRepository.getSong(id)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LyricsApplication)
                val songRepository = application.container.songRepository
                LyricsViewModel(songRepository = songRepository)
            }
        }
    }
}