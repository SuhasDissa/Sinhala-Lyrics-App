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
import app.suhasdissa.karoke.backend.repositories.Artist
import app.suhasdissa.karoke.backend.repositories.SongHeader
import app.suhasdissa.karoke.backend.repositories.SongRepository
import kotlinx.coroutines.launch

sealed interface FilterState {
    data class Success(val songs: ArrayList<SongHeader>) : FilterState
    object Loading : FilterState
    object Empty : FilterState
}

class ArtistFilterViewModel(private val songRepository: SongRepository) : ViewModel() {
    var filterState: FilterState by mutableStateOf(FilterState.Empty)
        private set
    var artist: Artist by mutableStateOf(Artist(artistID = 0, artistName = ""))
        private set

    fun filterArtist(id: Int) {
        viewModelScope.launch {
            filterState = FilterState.Loading
            filterState = FilterState.Success(
                songRepository.filterArtist(id)
            )

        }
    }

    fun getArtist(id: Int) {
        viewModelScope.launch {
            artist = songRepository.getArtist(id)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LyricsApplication)
                val songRepository = application.container.songRepository
                ArtistFilterViewModel(songRepository = songRepository)
            }
        }
    }
}