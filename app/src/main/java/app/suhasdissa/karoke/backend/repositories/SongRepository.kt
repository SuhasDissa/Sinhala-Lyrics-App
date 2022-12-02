package app.suhasdissa.karoke.backend.repositories

import app.suhasdissa.karoke.backend.database.ArtistEntity
import app.suhasdissa.karoke.backend.database.ArtistsDao
import app.suhasdissa.karoke.backend.database.SongEntity
import app.suhasdissa.karoke.backend.database.SongsDao

interface SongRepository {
    suspend fun getSongs(): ArrayList<SongHeader>
    suspend fun getArtists(): ArrayList<Artist>
    suspend fun getSong(id: Int): Song
    suspend fun getArtist(id: Int): Artist
    suspend fun searchSongs(search: String): ArrayList<SongHeader>
    suspend fun filterArtist(artist: Int): ArrayList<SongHeader>
}

data class Song(
    val _id: Int, val artistID: Int, val song: String, val lyric: String, val artistName: String
)

data class SongHeader(
    val _id: Int, val song: String, val artistName: String
)

data class Artist(
    val artistID: Int, val artistName: String
)

fun SongEntity.toSong() =
    Song(artistID = artistID, song = song, lyric = lyric, artistName = artistName, _id = _id)

fun ArtistEntity.toArtist() = Artist(artistID = artistID, artistName = artistName)

class LocalSongRepository(private val songsDao: SongsDao, private val artistsDao: ArtistsDao) :
    SongRepository {
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