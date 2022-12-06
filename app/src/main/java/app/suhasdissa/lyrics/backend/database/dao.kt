package app.suhasdissa.lyrics.backend.database

import androidx.room.*

data class SongHeader(
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "song", defaultValue = "") val song: String,
    @ColumnInfo(name = "artistName", defaultValue = "") val artistName: String
)

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs")
    fun getAll(): List<SongEntity>

    @Query("SELECT _id,song,artistName FROM songs")
    fun getSongs(): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE song LIKE :search")
    fun searchSongs(search:String): List<SongHeader>

    @Query("SELECT _id,song,artistName FROM songs WHERE artistID = :artist")
    fun filterArtist(artist:Int): List<SongHeader>

    @Query("SELECT * FROM songs WHERE _id = :id")
    fun getSong(id: Int): SongEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(memes: List<SongEntity>)

    @Delete
    fun delete(meme: SongEntity)
}

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM artists WHERE artistID = :artist")
    fun getArtist(artist:Int): ArtistEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(memes: List<ArtistEntity>)

    @Delete
    fun delete(meme: ArtistEntity)
}
