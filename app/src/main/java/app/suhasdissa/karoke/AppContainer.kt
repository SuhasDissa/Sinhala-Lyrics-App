package app.suhasdissa.karoke

import app.suhasdissa.karoke.backend.database.SongDatabase
import app.suhasdissa.karoke.backend.repositories.LocalSongRepository
import app.suhasdissa.karoke.backend.repositories.SongRepository

interface AppContainer {
    val songRepository: SongRepository
}

class DefaultAppContainer(database: SongDatabase) : AppContainer {
    override val songRepository: SongRepository by lazy { LocalSongRepository(database.songsDao(),database.aristsDao()) }
}