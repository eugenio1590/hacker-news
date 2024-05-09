package com.app.hackernews.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.hackernews.config.Constants.PAGE_SIZE
import com.app.hackernews.domain.Post
import com.app.hackernews.domain.PostRepository
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for managing the UI state and logic related to posts search.
 *
 * @param postRepository The repository responsible for providing access to post data.
 */
class ViewModel(private val postRepository: PostRepository) : androidx.lifecycle.ViewModel() {

    val posts: Flow<PagingData<Post>> by lazy {
        val config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)
        val pager = Pager(config = config) { PostPagingSource(postRepository) }
        return@lazy pager.flow.cachedIn(viewModelScope)
    }
}
