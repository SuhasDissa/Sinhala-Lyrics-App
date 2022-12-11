package app.suhasdissa.lyrics.backend.repositories

import android.util.Log
import app.suhasdissa.lyrics.backend.database.dao.SongsDao
import app.suhasdissa.lyrics.backend.database.entities.SongEntity
import app.suhasdissa.lyrics.backend.repositories.data.SongUpdate
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreSongUpdateRepository(
    private val songsDao: SongsDao, private val remoteDB: FirebaseFirestore
) : SongUpdateRepository {

    override fun updateLyrics() {
        remoteDB.collection("Lyrics").whereNotEqualTo("_id", 0).get().addOnSuccessListener { querySnapshot ->
            val songList = querySnapshot.toObjects(SongUpdate::class.java)
            if (songList.isNotEmpty()) {
                songsDao.insertAll(songList.map {
                    SongEntity(
                        artistID = it.artistID,
                        song = it.song,
                        lyric = it.lyric,
                        artistName = it.artistName,
                        _id = it._id
                    )
                })
            }
        }.addOnFailureListener { e ->
            Log.d("Firestore", e.toString())
        }
    }

    override fun editExistingLyric(update: SongUpdate) {
        val newLyric = HashMap<String, Any>()
        newLyric["_id"] = update._id
        newLyric["artistID"] = update.artistID
        newLyric["song"] = update.song
        newLyric["artistName"] = update.artistName
        newLyric["lyric"] = update.lyric

        remoteDB.collection("Lyrics").document(update._id.toString()).set(newLyric).addOnSuccessListener {
            // Successful write
        }.addOnFailureListener { e ->
            Log.d("Firestore", e.toString())
        }
    }
    override fun addNewLyric(update: SongUpdate) {
        val newLyric = HashMap<String, Any>()
        newLyric["_id"] = 0
        newLyric["artistID"] = update.artistID
        newLyric["song"] = update.song
        newLyric["artistName"] = update.artistName
        newLyric["lyric"] = update.lyric

        remoteDB.collection("Lyrics").add(newLyric).addOnSuccessListener {
            // Successful write
        }.addOnFailureListener { e ->
            Log.d("Firestore", e.toString())
        }
    }
}