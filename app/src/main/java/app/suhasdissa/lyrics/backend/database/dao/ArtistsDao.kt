package app.suhasdissa.lyrics.backend.database.dao

import androidx.room.*
import app.suhasdissa.lyrics.backend.database.entities.ArtistEntity

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM artists WHERE artistID = :artist")
    fun getArtist(artist: Int): ArtistEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(memes: List<ArtistEntity>)

    @Delete
    fun delete(meme: ArtistEntity)
}