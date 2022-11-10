package com.dev.core.comment.domain

import com.dev.core.account.domain.Account
import com.dev.core.comment.domain.core.BaseComment
import javax.persistence.Entity

@Entity
class CommentFree(
    parentId: Long?,
    message: String,
    account: Account,
    id: Long = 0L
) : BaseComment(
    parentId = parentId,
    message = message,
    account = account,
    id = id
)