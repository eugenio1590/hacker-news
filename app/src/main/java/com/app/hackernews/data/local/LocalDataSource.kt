package com.app.hackernews.data.local

import com.app.hackernews.domain.Post
import com.app.hackernews.domain.PostRepository

/**
 * Implementation of [PostRepository] for managing local data operations for posts.
 *
 * @param mapper The mapper responsible for converting data transfer objects (DTOs) to domain entities.
 * @param dao The Data Access Object (DAO) for accessing post entities in the local database.
 */
class LocalDataSource(
    private val mapper: PostMapper = PostMapper(),
    private val dao: PostDao
) : PostRepository {
    override suspend fun getAll(page: Int, limit: Int): List<Post> {
        val offset = (page - 1) * limit
        val list = dao.getAll(offset, limit)
        return list.map(mapper::toEntity)
    }

    override suspend fun saveAll(posts: List<Post>) {
        val list = posts.map(mapper::toDTO).toTypedArray()
        dao.saveAll(*list)
    }

    override suspend fun delete(post: Post) {
        val entity = dao.find(post.id)
        if (entity != null) {
            dao.update(entity.copy(isDeleted = true))
        }
    }
}
