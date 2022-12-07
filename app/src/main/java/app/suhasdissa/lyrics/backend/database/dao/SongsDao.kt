package app.suhasdissa.lyrics.backend.database.dao

import androidx.room.*
import app.suhasdissa.lyrics.backend.database.entities.SongEntity

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs")
    fun getAll(): List<SongEntity>

    @Query("SELECT _id,song,artistName FROM songs")
    fun getSongs(): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE song LIKE :search")
    fun searchSongs(search: String): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE artistID = :artist")
    fun filterArtist(artist: Int): List<SongHeader>

    @Query("SELECT * FROM songs WHERE _id = :id")
    fun getSong(id: Int): SongEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(memes: List<SongEntity>)

    @Delete
    fun delete(meme: SongEntity)
}