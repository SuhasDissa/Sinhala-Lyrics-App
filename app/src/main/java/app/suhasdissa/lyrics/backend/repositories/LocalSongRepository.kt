package app.suhasdissa.lyrics.backend.repositories

import app.suhasdissa.lyrics.backend.database.dao.ArtistsDao
import app.suhasdissa.lyrics.backend.database.dao.SongsDao
import app.suhasdissa.lyrics.backend.database.entities.ArtistEntity
import app.suhasdissa.lyrics.backend.database.entities.SongEntity
import app.suhasdissa.lyrics.backend.repositories.data.Artist
import app.suhasdissa.lyrics.backend.repositories.data.Song
import app.suhasdissa.lyrics.backend.repositories.data.SongHeader

class LocalSongRepository(private val songsDao: SongsDao, private val artistsDao: ArtistsDao) :
    SongRepository {

    fun SongEntity.toSong() =
        Song(artistID = artistID, song = song, lyric = lyric, artistName = artistName, _id = _id)

    fun ArtistEntity.toArtist() = Artist(artistID = artistID, artistName = artistName)

    override suspend fun getSongs(): ArrayList<SongHeader> {
        return songsDao.getSongs().mapTo(ArrayList()) {
            SongHeader(
                _id = it._id, song = it.song, artistName = it.artistName
            )
        }
    }

    override suspend fun getArtists(): ArrayList<Artist> {
        return artistsDao.getAll().mapTo(ArrayList()) {
            Artist(
                artistID = it.artistID, artistName = it.artistName
            )
        }
    }

    override suspend fun getSong(id: Int): Song {
        return songsDao.getSong(id).toSong()
    }

    override suspend fun getArtist(id: Int): Artist {
        return artistsDao.getArtist(id).toArtist()
    }

    override suspend fun searchSongs(search: String): ArrayList<SongHeader> {
        return songsDao.searchSongs(search).mapTo(ArrayList()) {
            SongHeader(
                _id = it._id, song = it.song, artistName = it.artistName
            )
        }
    }

    override suspend fun filterArtist(artist: Int): ArrayList<SongHeader> {
        return songsDao.filterArtist(artist).mapTo(ArrayList()) {
            SongHeader(
                _id = it._id, song = it.song, artistName = it.artistName
            )
        }
    }
}