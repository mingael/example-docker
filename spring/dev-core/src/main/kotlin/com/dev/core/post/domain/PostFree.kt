package com.dev.core.post.domain

import com.dev.core.account.domain.Account
import com.dev.core.post.domain.core.BasePost
import javax.persistence.Entity

@Entity
class PostFree(
    title: String,
    content: String,
    account: Account,
    id: Long = 0L
) : BasePost(
    title = title,
    content = content,
    account = account,
    id = id
)