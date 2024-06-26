package com.app.hackernews.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.app.hackernews.presentation.ViewModel
import com.github.marlonlom.utilities.timeago.TimeAgo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(viewModel: ViewModel, onPostSelect: (url: String?) -> Unit) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullToRefreshState()
    val isLoading = posts.loadState.append is LoadState.Loading

    if (pullRefreshState.isRefreshing && !isLoading) {
        posts.refresh()
    }

    if (posts.loadState.refresh is LoadState.NotLoading) {
        pullRefreshState.endRefresh()
    }

    Box(
        modifier = Modifier.nestedScroll(pullRefreshState.nestedScrollConnection),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn {
            items(
                posts.itemCount,
                key = posts.itemKey { it.id }
            ) { index ->
                val post = posts[index]
                if (post != null) {
                    ListItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { onPostSelect(post.url) },
                        headlineContent = { Text(post.title) },
                        supportingContent = {
                            val timeAgo = TimeAgo.using(post.createdAt)
                            Text("${post.author} - $timeAgo")
                        }
                    )
                    if (index < posts.itemCount) {
                        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
                    }
                }
            }
        }

        if (isLoading) {
            LinearProgressIndicator(Modifier.fillMaxWidth().align(Alignment.BottomCenter))
        }

        PullToRefreshContainer(pullRefreshState)
    }
}
