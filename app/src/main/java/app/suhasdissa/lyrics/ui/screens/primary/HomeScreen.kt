package app.suhasdissa.lyrics.ui.screens.primary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.suhasdissa.lyrics.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickSongCard: (url: Int) -> Unit,
    onClickArtistCard: (id: Int) -> Unit,
    onClickSettings: () -> Unit,
    onClickSearch: () -> Unit
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = { onClickSettings() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = "Search"
                )
            }

        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { onClickSearch() }) {
            Icon(painterResource(R.drawable.ic_search), contentDescription = "Search")
        }
    }) { innerPadding ->
        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
                    Text(
                        "Songs",
                        modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
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
                    Text(
                        "Artists",
                        modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            HorizontalPager(
                count = 2,
                state = pagerState,
                modifier = modifier.fillMaxSize()
            ) { index ->
                when (index) {
                    0 -> SongsScreen(modifier, onClickTextCard = onClickSongCard)
                    1 -> ArtistScreen(modifier, onClickTextCard = onClickArtistCard)
                }
            }
        }
    }
}