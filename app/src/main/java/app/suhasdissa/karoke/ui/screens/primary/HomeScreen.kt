package app.suhasdissa.karoke.ui.screens.primary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickSongCard: (url: Int) -> Unit,
    onClickArtistCard: (id: Int) -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column(modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pagerState.currentPage, modifier.fillMaxWidth()) {
            Tab(
                modifier = modifier.weight(1f),
                selected = (pagerState.currentPage == 0),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            0
                        )
                    }
                }) {
                Text("Songs",modifier.padding(10.dp), style = MaterialTheme.typography.titleLarge)
            }
            Tab(
                modifier = modifier.weight(1f),
                selected = (pagerState.currentPage == 1),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            1
                        )
                    }
                }) {
                Text("Artists",modifier.padding(10.dp), style = MaterialTheme.typography.titleLarge)
            }
        }
        HorizontalPager(count = 2, state = pagerState, modifier = modifier.fillMaxSize()) { index ->
            when (index) {
                0 -> SongsScreen(modifier, onClickTextCard = onClickSongCard)
                1 -> ArtistScreen(modifier, onClickTextCard = onClickArtistCard)
            }
        }
    }
}