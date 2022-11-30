package app.suhasdissa.karoke.ui.screens.primary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.karoke.R
import app.suhasdissa.karoke.backend.repositories.SongHeader
import app.suhasdissa.karoke.backend.viewmodels.SearchState
import app.suhasdissa.karoke.backend.viewmodels.SearchViewModel
import app.suhasdissa.karoke.ui.components.LoadingScreen
import app.suhasdissa.karoke.ui.components.TextCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    val search = remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier.fillMaxSize()) {
        Row(modifier.padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center) {
            TextField(
                value = search.value,
                onValueChange = {
                    search.value = it
                    if (search.value.text.length >= 3) {
                        searchViewModel.searchSongs(search.value.text)
                    }
                },
                modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Search Songs") },
                shape = CircleShape
            )
        }

        when (val searchState = searchViewModel.searchState) {
            is SearchState.Empty -> EmptyScreen(modifier)
            is SearchState.Loading -> LoadingScreen(modifier)
            is SearchState.Success -> SearchGrid(
                searchState.songs, modifier, onClickTextCard
            )
        }
    }
}

@Composable
private fun SearchGrid(
    songs: ArrayList<SongHeader>, modifier: Modifier = Modifier, onClickTextCard: (url: Int) -> Unit
) {
    if (songs.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
        ) {

            Text(
                stringResource(R.string.no_results), style = MaterialTheme.typography.bodyLarge
            )

        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(500.dp),
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(items = songs) { item ->
                TextCard(
                    clickAction = { onClickTextCard(item._id) },
                    song = item.song,
                    artist = item.artistName
                )
            }

        }
    }
}

@Composable
private fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
    ) {

        Text(
            stringResource(R.string.search_for_songs), style = MaterialTheme.typography.bodyLarge
        )

    }
}