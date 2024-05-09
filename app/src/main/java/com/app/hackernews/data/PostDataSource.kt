package com.app.hackernews.data

import com.app.hackernews.domain.Post
import com.app.hackernews.domain.PostRepository

/**
 * Data source responsible for fetching and storing posts, combining both remote and local sources.
 *
 * @param localDataSource The local data source for posts.
 * @param remoteDataSource The remote data source for posts.
 */
class PostDataSource(
    private val localDataSource: PostRepository,
    private val remoteDataSource: PostRepository
) : PostRepository {

    /**
     * Retrieves a page of posts from the remote data source, saves them locally, and returns them.
     *
     * @param page The page number to retrieve.
     * @param limit The maximum number of posts per page.
     * @return A list of posts for the specified page.
     */
    override suspend fun getAll(page: Int, limit: Int): List<Post> {
        val posts = remoteDataSource.getAll(page, limit)
        localDataSource.saveAll(posts)
        return localDataSource.getAll(page, limit)
    }

    override suspend fun saveAll(posts: List<Post>) {
        localDataSource.saveAll(posts)
    }

    override suspend fun delete(post: Post) {
        localDataSource.delete(post)
    }
}
