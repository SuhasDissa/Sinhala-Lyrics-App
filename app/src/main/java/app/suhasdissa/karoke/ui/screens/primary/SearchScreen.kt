package app.suhasdissa.karoke.ui.screens.primary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.karoke.R
import app.suhasdissa.karoke.backend.viewmodels.SearchState
import app.suhasdissa.karoke.backend.viewmodels.SearchViewModel
import app.suhasdissa.karoke.ui.components.LoadingScreen
import app.suhasdissa.karoke.ui.components.MessageScreen
import app.suhasdissa.karoke.ui.components.SongGrid

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
            is SearchState.Empty -> MessageScreen(R.string.search_for_songs, modifier)
            is SearchState.Loading -> LoadingScreen(modifier)
            is SearchState.Success -> if (searchState.songs.isEmpty()) {
                MessageScreen(R.string.no_results, modifier)
            } else {
                SongGrid(
                    searchState.songs, modifier, onClickTextCard
                )
            }
        }
    }
}