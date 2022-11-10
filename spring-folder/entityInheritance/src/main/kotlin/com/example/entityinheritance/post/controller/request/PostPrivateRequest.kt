package com.example.entityinheritance.post.controller.request

import com.example.entityinheritance.post.domain.PostPrivate

data class PostPrivateRequest(
    val title: String,
    val content: String,
    val category: String
) {
    fun toEntity() = PostPrivate(
        title = title,
        content = content,
        category = category
    )
}