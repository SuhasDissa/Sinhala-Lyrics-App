package app.suhasdissa.lyrics.backend.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey val artistID: Int,
    @ColumnInfo(name = "artistName", defaultValue = "") val artistName: String
)