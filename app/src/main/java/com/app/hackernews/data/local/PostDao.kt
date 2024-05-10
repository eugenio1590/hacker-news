package com.app.hackernews.data.local

import androidx.room.*

/**
 * Data Access Object (DAO) for accessing post entities in the local database.
 */
@Dao
interface PostDao {
    /**
     * Retrieves a post entity by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The post entity if found, otherwise null.
     */
    @Query("SELECT * FROM posts WHERE id = :id LIMIT 1")
    suspend fun find(id: String): PostEntity?

    /**
     * Retrieves a list of post entities with pagination support.
     *
     * @param offset The offset from the beginning of the results.
     * @param limit The maximum number of post entities to retrieve.
     * @return A list of post entities within the specified range.
     */
    @Query("SELECT * FROM posts WHERE is_deleted = '0' ORDER BY created_at LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(vararg posts: PostEntity)

    @Update
    suspend fun update(post: PostEntity)
}
