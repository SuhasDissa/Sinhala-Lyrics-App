package app.suhasdissa.karoke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.suhasdissa.karoke.ui.LyricsApp
import app.suhasdissa.karoke.ui.theme.LyricsAppTheme

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