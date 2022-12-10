package app.suhasdissa.lyrics.backend.repositories.data


data class SongUpdate(
    val _id: Int, val artistID: Int, val artistName: String, val lyric: String, val song: String
) {
    constructor() : this(0, 0, "", "", "")
}
