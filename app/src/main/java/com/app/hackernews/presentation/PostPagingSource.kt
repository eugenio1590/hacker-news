package com.app.hackernews.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.hackernews.config.Constants.FIRST_PAGE
import com.app.hackernews.domain.Post
import com.app.hackernews.domain.PostRepository

/**
 * A PagingSource implementation for retrieving paginated [Post] data.
 * This class serves as a data source for the Paging 3 library.
 *
 * @param postRepository The repository responsible for fetching [Post] objects.
 */
class PostPagingSource(private val postRepository: PostRepository) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        try {
            val page = params.key ?: FIRST_PAGE
            val results = postRepository.getAll(page = page, limit = params.loadSize)

            return LoadResult.Page(
                data = results,
                prevKey = null, // Only paging forward.
                nextKey = if (results.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}