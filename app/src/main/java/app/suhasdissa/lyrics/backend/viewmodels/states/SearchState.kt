package app.suhasdissa.lyrics.backend.viewmodels.states

import app.suhasdissa.lyrics.backend.repositories.data.SongHeader

sealed interface SearchState {
    data class Success(val songs: ArrayList<SongHeader>) : SearchState
    object Empty : SearchState
}