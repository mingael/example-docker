package com.example.entityinheritance.post.controller.request

import com.example.entityinheritance.post.domain.PostPublic

data class PostPublicRequest(
    val title: String,
    val content: String,
    val tag: String
) {
    fun toEntity() = PostPublic(
        title = title,
        content = content,
        tag = tag
    )
}