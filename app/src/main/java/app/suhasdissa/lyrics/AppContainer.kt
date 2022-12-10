package app.suhasdissa.lyrics

import app.suhasdissa.lyrics.backend.database.SongDatabase
import app.suhasdissa.lyrics.backend.repositories.FirestoreSongUpdateRepository
import app.suhasdissa.lyrics.backend.repositories.LocalSongRepository
import app.suhasdissa.lyrics.backend.repositories.SongRepository
import app.suhasdissa.lyrics.backend.repositories.SongUpdateRepository
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val songRepository: SongRepository
    val songUpdateRepository: SongUpdateRepository
}

class DefaultAppContainer(database: SongDatabase, remoteDB: FirebaseFirestore) : AppContainer {
    override val songRepository: SongRepository by lazy {
        LocalSongRepository(
            database.songsDao(), database.artistsDao()
        )
    }
    override val songUpdateRepository: SongUpdateRepository by lazy {
        FirestoreSongUpdateRepository(
            database.songsDao(), remoteDB
        )
    }
}