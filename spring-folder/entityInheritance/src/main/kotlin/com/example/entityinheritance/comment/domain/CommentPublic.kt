package com.example.entityinheritance.comment.domain

import com.example.entityinheritance.comment.domain.core.BaseComment
import javax.persistence.Entity

@Entity
class CommentPublic(
    parentId: Long?,
    message: String,
    val nickname: String
) : BaseComment(
    parentId = parentId,
    message = message
)