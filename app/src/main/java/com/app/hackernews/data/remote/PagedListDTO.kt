package com.app.hackernews.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) representing a paginated list of post.
 *
 * This class is used for transferring a paginated list of post data between different layers
 * of the application, such as the remote service and the data layer.
 *
 * @property posts The list of [PostDTO] objects representing the paginated post data.
 */
@Serializable
data class PagedListDTO(
    @SerialName("hits")
    val posts: List<PostDTO>
): Collection<PostDTO> by posts
