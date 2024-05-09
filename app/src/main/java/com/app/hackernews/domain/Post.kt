package com.app.hackernews.domain

import java.time.LocalDateTime

/**
 * Class representing a post on Hacker News.
 *
 * @property id The unique identifier of the post.
 * @property title Title of the post.
 * @property author Author of the post.
 * @property url URL associated with the post, can be null.
 * @property createdAt Date and time the post was created.
 */
data class Post(
    val id: String,
    val title: String,
    val author: String,
    val url: String?,
    val createdAt: LocalDateTime
)
