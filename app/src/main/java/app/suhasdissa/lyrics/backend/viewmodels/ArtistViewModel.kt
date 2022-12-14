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
import app.suhasdissa.lyrics.backend.database.entities.Artist
import app.suhasdissa.lyrics.backend.repositories.SongRepository
import kotlinx.coroutines.launch

class ArtistViewModel(private val songRepository: SongRepository) : ViewModel() {
    var artists: List<Artist> by mutableStateOf(listOf())
        private set

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            artists = songRepository.getArtists()


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LyricsApplication)
                val songRepository = application.container.songRepository
                ArtistViewModel(songRepository = songRepository)
            }
        }
    }
}