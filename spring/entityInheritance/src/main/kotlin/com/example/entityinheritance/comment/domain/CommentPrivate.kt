package com.example.entityinheritance.comment.domain

import com.example.entityinheritance.comment.domain.core.BaseComment
import javax.persistence.Entity

@Entity
class CommentPrivate(
    parentId: Long?,
    message: String,
    val ip: String
) : BaseComment(
    parentId = parentId,
    message = message
)