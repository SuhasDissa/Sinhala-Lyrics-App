package app.suhasdissa.lyrics.backend.database.dao

import androidx.room.ColumnInfo

data class SongHeader(
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "song", defaultValue = "") val song: String,
    @ColumnInfo(name = "artistName", defaultValue = "") val artistName: String
)