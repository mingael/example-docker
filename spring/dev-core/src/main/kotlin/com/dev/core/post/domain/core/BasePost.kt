package com.dev.core.post.domain.core

import com.dev.core.account.domain.Account
import javax.persistence.*

@MappedSuperclass
abstract class BasePost(
    val title: String,

    val content: String,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
)