package com.app.hackernews.data.remote

import com.app.hackernews.domain.Post
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Mapper class for converting between PostDTO and Post entities.
 *
 * This class provides methods for mapping data transfer objects (DTOs) to domain entities and vice-versa.
 */
class PostMapper {

    fun toEntity(dto: PostDTO): Post {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val createdAt = dateFormat.parse(dto.createdAt) ?: throw Error("created_at could not be null")
        return Post(
            id = dto.id,
            title = dto.title,
            author = dto.author,
            url = dto.url,
            createdAt = createdAt.time
        )
    }

    fun toDTO(entity: Post): PostDTO {
        throw UnsupportedOperationException("Unimplemented method")
    }
}