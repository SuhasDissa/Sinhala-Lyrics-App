package app.suhasdissa.lyrics.backend.database.dao

import androidx.room.*
import app.suhasdissa.lyrics.backend.database.entities.Artist

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists")
    fun getAll(): List<Artist>

    @Query("SELECT * FROM artists WHERE artistID = :artist")
    fun getArtist(artist: Int): Artist

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(memes: List<Artist>)

    @Delete
    fun delete(meme: Artist)
}