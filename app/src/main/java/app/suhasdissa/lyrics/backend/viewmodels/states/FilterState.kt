package app.suhasdissa.lyrics.backend.viewmodels.states

import app.suhasdissa.lyrics.backend.database.dao.SongHeader

sealed interface FilterState {
    data class Success(val songs: List<SongHeader>) : FilterState
    object Empty : FilterState
}