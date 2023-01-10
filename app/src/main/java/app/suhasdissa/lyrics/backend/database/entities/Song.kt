package app.suhasdissa.lyrics.backend.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @ColumnInfo(name = "artistID", defaultValue = "") val artistID: Int,
    @ColumnInfo(name = "song", defaultValue = "") val song: String,
    @ColumnInfo(name = "lyric", defaultValue = "") val lyric: String,
    @ColumnInfo(name = "artistName", defaultValue = "") val artistName: String

)