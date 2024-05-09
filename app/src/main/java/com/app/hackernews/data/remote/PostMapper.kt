package com.app.hackernews.data.remote

import com.app.hackernews.domain.Post
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Mapper class for converting between PostDTO and Post entities.
 *
 * This class provides methods for mapping data transfer objects (DTOs) to domain entities and vice-versa.
 */
class PostMapper {

    private companion object {
        val DATE_FORMATER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }

    fun toEntity(dto: PostDTO): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            author = dto.author,
            url = dto.url,
            createdAt = LocalDateTime.parse(dto.createdAt, DATE_FORMATER)
        )
    }

    fun toDTO(entity: Post): PostDTO {
        throw UnsupportedOperationException("Unimplemented method")
    }
}