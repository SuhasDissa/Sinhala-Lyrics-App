package app.suhasdissa.lyrics.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.lyrics.LyricsApplication
import app.suhasdissa.lyrics.backend.repositories.SongUpdateRepository
import app.suhasdissa.lyrics.backend.repositories.data.SongUpdate
import kotlinx.coroutines.launch

class AddSongViewModel(private val songUpdateRepository: SongUpdateRepository) : ViewModel() {
    fun addSong(song: SongUpdate) {
        viewModelScope.launch {
            songUpdateRepository.addNewLyric(song)
        }
    }
    fun editSong(song: SongUpdate) {
        viewModelScope.launch {
            songUpdateRepository.editExistingLyric(song)
        }
    }
    fun updateSongs() {
        viewModelScope.launch {
            songUpdateRepository.updateLyrics()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LyricsApplication)
                val songUpdateRepository = application.container.songUpdateRepository
                AddSongViewModel(songUpdateRepository = songUpdateRepository)
            }
        }
    }
}