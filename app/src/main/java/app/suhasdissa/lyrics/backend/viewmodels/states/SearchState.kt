package app.suhasdissa.lyrics.backend.viewmodels.states

import app.suhasdissa.lyrics.backend.database.dao.SongHeader

sealed interface SearchState {
    data class Success(val songs: List<SongHeader>) : SearchState
    object Empty : SearchState
}