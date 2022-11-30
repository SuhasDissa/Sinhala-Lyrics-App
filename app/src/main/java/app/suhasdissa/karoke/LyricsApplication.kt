package app.suhasdissa.karoke

import android.app.Application
import app.suhasdissa.karoke.backend.database.SongDatabase

class LyricsApplication : Application() {
    private val database by lazy { SongDatabase.getDatabase(this) }
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(database)
    }
}