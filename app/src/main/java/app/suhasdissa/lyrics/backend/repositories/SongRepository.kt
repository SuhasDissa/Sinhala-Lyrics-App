package app.suhasdissa.lyrics.backend.repositories

import app.suhasdissa.lyrics.backend.database.dao.SongHeader
import app.suhasdissa.lyrics.backend.database.entities.Artist
import app.suhasdissa.lyrics.backend.database.entities.Song

interface SongRepository {
    suspend fun getSongs(): List<SongHeader>
    suspend fun getArtists(): List<Artist>
    suspend fun getSong(id: Int): Song
    suspend fun getArtist(id: Int): Artist
    suspend fun searchSongs(search: String): List<SongHeader>
    suspend fun filterArtist(artist: Int): List<SongHeader>
}