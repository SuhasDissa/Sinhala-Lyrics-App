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
import app.suhasdissa.lyrics.backend.repositories.SongHeader
import app.suhasdissa.lyrics.backend.repositories.SongRepository
import kotlinx.coroutines.launch

sealed interface SearchState {
    data class Success(val songs: ArrayList<SongHeader>) : SearchState
    object Loading : SearchState
    object Empty : SearchState
}

class SearchViewModel(private val songRepository: SongRepository) : ViewModel() {
    var searchState: SearchState by mutableStateOf(SearchState.Empty)
        private set

    fun searchSongs(search: String) {
        val searchQuery = search.split(" ").joinToString("%")
        viewModelScope.launch {
            searchState = SearchState.Loading
            searchState = SearchState.Success(
                songRepository.searchSongs("%$searchQuery%")
            )

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LyricsApplication)
                val songRepository = application.container.songRepository
                SearchViewModel(songRepository = songRepository)
            }
        }
    }
}