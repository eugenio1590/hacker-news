package com.app.hackernews.data.remote

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * Data Transfer Object (DTO) representing a post.
 *
 * This class is used for transferring post data between different layers of the application,
 * such as the remote service and the data layer.
 *
 * @property id The unique identifier of the post.
 * @property title Title of the post.
 * @property author Author of the post.
 * @property url URL associated with the post.
 * @property createdAt The date when the post was created.
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class PostDTO(
    @SerialName("objectID")
    val id: String,
    @SerialName("story_title")
    @JsonNames("title")
    val title: String,
    @SerialName("author")
    val author: String,
    @SerialName("story_url")
    @JsonNames("url")
    val url: String? = null,
    @SerialName("created_at")
    val createdAt: String
)
