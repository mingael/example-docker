package com.dev.core.comment.domain.core

import com.dev.core.account.domain.Account
import javax.persistence.*

@MappedSuperclass
abstract class BaseComment(
    val parentId: Long?,

    val message: String,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
)