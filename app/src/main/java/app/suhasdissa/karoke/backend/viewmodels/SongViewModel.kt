package app.suhasdissa.karoke.backend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.karoke.LyricsApplication
import app.suhasdissa.karoke.backend.repositories.SongHeader
import app.suhasdissa.karoke.backend.repositories.SongRepository
import kotlinx.coroutines.launch

sealed interface SongState {
    data class Success(val songs: ArrayList<SongHeader>) : SongState
    object Loading : SongState
}

class SongsViewModel(private val songRepository: SongRepository) : ViewModel() {
    var songState: SongState by mutableStateOf(SongState.Loading)
        private set

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            songState = SongState.Loading
            songState = SongState.Success(
                songRepository.getSongs()
            )

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