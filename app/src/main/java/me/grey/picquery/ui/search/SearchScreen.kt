package me.grey.picquery.ui.search

import SearchInput
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.InternalTextApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.grey.picquery.data.model.Photo
import me.grey.picquery.ui.albums.AlbumViewModel

@OptIn(InternalTextApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    initialQuery: String,
    onClickPhoto: (Photo, Int) -> Unit,
    onBack: () -> Unit,
    onSelectSearchTarget: () -> Unit,
    onSelectSearchRange: () -> Unit,
    onSearch: (String) -> Unit,
    searchResult: List<Photo>,
    searchState: SearchState,
) {
    val queryText by remember { mutableStateOf(initialQuery) }
    LaunchedEffect(Unit) {
        onSearch(initialQuery)
    }
    Column() {
        SearchInput(
            onStartSearch = { onSearch(it) },
            queryText = queryText,
            onSelectSearchRange = onSelectSearchRange,
            onSelectSearchTarget = onSelectSearchTarget,
            leadingIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                    )
                }
            }
        )
        SearchResultGrid(
            resultList = searchResult,
            state = searchState,
            onClickPhoto = onClickPhoto
        )
    }

}

@Composable
private fun TopBarActions(
    albumViewModel: AlbumViewModel = viewModel(),
    searchViewModel: SearchViewModel = viewModel()
) {
    val size = Modifier.size(22.dp)
    IconButton(onClick = { albumViewModel.openBottomSheet() }) {
        Icon(
            modifier = size,
            imageVector = Icons.Default.AddCircle,
            contentDescription = null
        )
    }
    IconButton(onClick = { searchViewModel.openFilterBottomSheet() }) {
        Icon(
            modifier = size,
            imageVector = Icons.Rounded.FilterList,
            contentDescription = null
        )
    }
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            modifier = size,
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )
    }
}


