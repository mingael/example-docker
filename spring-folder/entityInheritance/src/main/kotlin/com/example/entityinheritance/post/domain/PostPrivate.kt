package com.example.entityinheritance.post.domain

import com.example.entityinheritance.post.domain.core.BasePost
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("private")
class PostPrivate(
    title: String,
    content: String,
    val category: String
) : BasePost(
    title = title,
    content = content
)