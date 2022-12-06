package app.suhasdissa.lyrics.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SongEntity::class, ArtistEntity::class], version = 1, exportSchema = true,)
abstract class SongDatabase : RoomDatabase() {

    abstract fun songsDao(): SongsDao
    abstract fun aristsDao(): ArtistsDao

    companion object {
        @Volatile
        private var INSTANCE: SongDatabase? = null

        fun getDatabase(context: Context): SongDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, SongDatabase::class.java, "database"
                ).createFromAsset("databases/database.db").fallbackToDestructiveMigration().allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}