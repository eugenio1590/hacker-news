package com.app.hackernews.data.local

import com.app.hackernews.domain.Post
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Mapper class for converting between PostEntity (db) and Post entities.
 *
 * This class provides methods for mapping data transfer objects (DTOs) to domain entities and vice-versa.
 */
class PostMapper {
    private companion object {
        val DATE_FORMATER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }

    fun toEntity(dto: PostEntity): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            author = dto.author,
            url = dto.url,
            createdAt = LocalDateTime.parse(dto.createdAt, DATE_FORMATER)
        )
    }

    fun toDTO(entity: Post): PostEntity {
        return PostEntity(
            id = entity.id,
            title = entity.title,
            author = entity.author,
            url = entity.url,
            createdAt = entity.createdAt.format(DATE_FORMATER),
            isDeleted = false
        )
    }
}