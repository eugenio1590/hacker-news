package com.app.hackernews.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a post in the database.
 *
 * @param id Unique identifier of the post.
 * @param title Title of the post.
 * @param author Name of the author of the post.
 * @param url URL associated with the post, can be null.
 * @param createdAt Date and time when the post was created.
 * @param isDeleted Indicates whether the post has been deleted.
 */
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val author: String,
    val url: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean
)
