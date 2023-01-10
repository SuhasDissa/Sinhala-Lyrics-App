package app.suhasdissa.lyrics.backend.repositories

import app.suhasdissa.lyrics.backend.database.dao.ArtistsDao
import app.suhasdissa.lyrics.backend.database.dao.SongHeader
import app.suhasdissa.lyrics.backend.database.dao.SongsDao
import app.suhasdissa.lyrics.backend.database.entities.Artist
import app.suhasdissa.lyrics.backend.database.entities.Song

class LocalSongRepository(private val songsDao: SongsDao, private val artistsDao: ArtistsDao) :
    SongRepository {

    override suspend fun getSongs(): List<SongHeader> {
        return songsDao.getSongs()
    }

    override suspend fun getArtists(): List<Artist> {
        return artistsDao.getAll()
    }

    override suspend fun getSong(id: Int): Song {
        return songsDao.getSong(id)
    }

    override suspend fun getArtist(id: Int): Artist {
        return artistsDao.getArtist(id)
    }

    override suspend fun searchSongs(search: String): List<SongHeader> {
        return songsDao.searchSongs(search)
    }

    override suspend fun filterArtist(artist: Int): List<SongHeader> {
        return songsDao.filterArtist(artist)
    }
}