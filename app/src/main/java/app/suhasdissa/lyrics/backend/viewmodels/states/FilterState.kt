package app.suhasdissa.lyrics.backend.viewmodels.states

import app.suhasdissa.lyrics.backend.repositories.data.SongHeader

sealed interface FilterState {
    data class Success(val songs: ArrayList<SongHeader>) : FilterState
    object Empty : FilterState
}