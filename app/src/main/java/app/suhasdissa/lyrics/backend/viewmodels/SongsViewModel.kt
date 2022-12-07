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
import app.suhasdissa.lyrics.backend.repositories.data.SongHeader
import kotlinx.coroutines.launch

class SongsViewModel(private val songRepository: SongRepository) : ViewModel() {
    var songs: ArrayList<SongHeader> by mutableStateOf(arrayListOf())
        private set

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            songs = songRepository.getSongs()


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LyricsApplication)
                val songRepository = application.container.songRepository
                SongsViewModel(songRepository = songRepository)
            }
        }
    }
}