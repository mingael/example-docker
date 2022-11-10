package com.dev.core.board.domain

import com.dev.core.board.domain.core.BaseBoard
import javax.persistence.Entity

@Entity
class BoardFree(
    title: String,
    permit: String,
    id: Long = 0L
) : BaseBoard(
    title = title,
    permit = permit,
    id = id
)