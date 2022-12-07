package app.suhasdissa.lyrics.backend.repositories.data

data class Song(
    val _id: Int, val artistID: Int, val song: String, val lyric: String, val artistName: String
)