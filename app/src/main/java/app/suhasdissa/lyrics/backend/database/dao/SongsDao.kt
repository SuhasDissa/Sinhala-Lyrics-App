package app.suhasdissa.lyrics.backend.database.dao

import androidx.room.*
import app.suhasdissa.lyrics.backend.database.entities.Song

@Dao
interface SongsDao {
    @Query("SELECT _id,song,artistName FROM songs")
    fun getSongs(): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE song LIKE :search")
    fun searchSongs(search: String): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE artistID = :artist")
    fun filterArtist(artist: Int): List<SongHeader>

    @Query("SELECT * FROM songs WHERE _id = :id")
    fun getSong(id: Int): Song

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(memes: List<Song>)
}