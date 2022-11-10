package com.dev.core.board.domain.core

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class BaseBoard(
    open val title: String,

    open val permit: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long
)