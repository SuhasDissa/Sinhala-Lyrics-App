package app.suhasdissa.lyrics.backend.repositories

import app.suhasdissa.lyrics.backend.repositories.data.Artist
import app.suhasdissa.lyrics.backend.repositories.data.Song
import app.suhasdissa.lyrics.backend.repositories.data.SongHeader

interface SongRepository {
    suspend fun getSongs(): ArrayList<SongHeader>
    suspend fun getArtists(): ArrayList<Artist>
    suspend fun getSong(id: Int): Song
    suspend fun getArtist(id: Int): Artist
    suspend fun searchSongs(search: String): ArrayList<SongHeader>
    suspend fun filterArtist(artist: Int): ArrayList<SongHeader>
}