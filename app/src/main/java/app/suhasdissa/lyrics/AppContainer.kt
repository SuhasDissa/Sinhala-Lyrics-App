package app.suhasdissa.lyrics

import app.suhasdissa.lyrics.backend.database.SongDatabase
import app.suhasdissa.lyrics.backend.repositories.LocalSongRepository
import app.suhasdissa.lyrics.backend.repositories.SongRepository

interface AppContainer {
    val songRepository: SongRepository
}

class DefaultAppContainer(database: SongDatabase) : AppContainer {
    override val songRepository: SongRepository by lazy { LocalSongRepository(database.songsDao(),database.artistsDao()) }
}