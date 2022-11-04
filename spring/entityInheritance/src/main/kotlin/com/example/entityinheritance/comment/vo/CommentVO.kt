package com.example.entityinheritance.comment.vo

data class CommentVO(
    val id: Long = 0L,
    val parentId: Long? = null,
    val message: String = ""
)
