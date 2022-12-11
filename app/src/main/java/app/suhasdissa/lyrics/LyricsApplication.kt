package app.suhasdissa.lyrics

import android.app.Application
import app.suhasdissa.lyrics.backend.database.SongDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class LyricsApplication : Application() {
    private val database by lazy { SongDatabase.getDatabase(this) }
    private val remoteDB by lazy {
        FirebaseFirestore.getInstance().apply {
            firestoreSettings =
                FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build()
        }
    }
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(database, remoteDB)
    }
}