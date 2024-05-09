package com.app.hackernews.data.remote

import com.app.hackernews.domain.Post
import com.app.hackernews.domain.PostRepository

/**
 * Implementation of [PostRepository] that retrieves episode data from a remote service.
 *
 * @param mapper The mapper responsible for converting data transfer objects (DTOs) to domain entities.
 * @param service The service providing access to the remote data.
 */
class RemoteDataSource(
    private val mapper: PostMapper,
    private val service: PostService
) : PostRepository {
    override suspend fun getAll(page: Int, limit: Int): List<Post> {
        val response = service.fetchPosts(page, limit)
        val list = response.body() ?: emptyList()
        return list.map(mapper::toEntity)
    }

    override suspend fun saveAll(posts: List<Post>) {
        throw UnsupportedOperationException("'saveAll' method is not supported in remote data source")
    }

    override suspend fun delete(post: Post) {
        throw UnsupportedOperationException("'delete' method is not supported in remote data source")
    }
}
