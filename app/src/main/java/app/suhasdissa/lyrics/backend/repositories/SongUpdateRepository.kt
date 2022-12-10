package app.suhasdissa.lyrics.backend.repositories

import app.suhasdissa.lyrics.backend.repositories.data.SongUpdate

interface SongUpdateRepository {

    fun updateLyrics()
    fun uploadNewLyric(update: SongUpdate)
}