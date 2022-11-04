package com.example.entityinheritance.post.domain

import com.example.entityinheritance.post.domain.core.BasePost
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("public")
class PostPublic(
    title: String,
    content: String,
    val tag: String
) : BasePost(
    title = title,
    content = content
)