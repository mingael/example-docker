package com.example.entityinheritance.post.domain.core

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "POST_TYPE")
abstract class BasePost(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    open val id: Long = 0L,

    open val title: String,

    open val content: String
)