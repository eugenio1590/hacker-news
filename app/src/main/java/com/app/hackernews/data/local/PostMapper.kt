package com.app.hackernews.data.local

import com.app.hackernews.domain.Post

/**
 * Mapper class for converting between PostEntity (db) and Post entities.
 *
 * This class provides methods for mapping data transfer objects (DTOs) to domain entities and vice-versa.
 */
class PostMapper {
    fun toEntity(dto: PostEntity): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            author = dto.author,
            url = dto.url,
            createdAt = dto.createdAt
        )
    }

    fun toDTO(entity: Post): PostEntity {
        return PostEntity(
            id = entity.id,
            title = entity.title,
            author = entity.author,
            url = entity.url,
            createdAt = entity.createdAt,
            isDeleted = false
        )
    }
}