package com.app.hackernews.domain

/**
 * Interface for managing posts.
 */
interface PostRepository {
    /**
     * Retrieves a page of posts that are not deleted by the user.
     *
     * @param page The page number to retrieve.
     * @param limit The maximum number of posts per page.
     * @return A list of posts for the specified page.
     */
    suspend fun getAll(page: Int, limit: Int): List<Post>

    /**
     * Saves a list of posts in the data source.
     *
     * @param posts The list of posts to save.
     */
    suspend fun saveAll(posts: List<Post>)

    /**
     * Deletes a post.
     */
    suspend fun delete(post: Post)
}
