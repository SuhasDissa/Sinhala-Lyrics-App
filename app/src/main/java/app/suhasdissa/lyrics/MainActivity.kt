package app.suhasdissa.lyrics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.suhasdissa.lyrics.ui.LyricsApp
import app.suhasdissa.lyrics.ui.theme.LyricsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsAppTheme {
                LyricsApp()
            }
        }
    }
}