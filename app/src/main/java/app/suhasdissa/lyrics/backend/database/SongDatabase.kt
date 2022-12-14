package app.suhasdissa.lyrics.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.suhasdissa.lyrics.backend.database.dao.ArtistsDao
import app.suhasdissa.lyrics.backend.database.dao.SongsDao
import app.suhasdissa.lyrics.backend.database.entities.Artist
import app.suhasdissa.lyrics.backend.database.entities.Song

@Suppress("TrailingComma")
@Database(entities = [Song::class, Artist::class], version = 1, exportSchema = true,)
abstract class SongDatabase : RoomDatabase() {

    abstract fun songsDao(): SongsDao
    abstract fun artistsDao(): ArtistsDao

    companion object {
        @Volatile
        private var INSTANCE: SongDatabase? = null

        fun getDatabase(context: Context): SongDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, SongDatabase::class.java, "database"
                ).createFromAsset("databases/database.db").fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}