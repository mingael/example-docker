package com.dev.core.comment.repository

import com.dev.core.comment.domain.CommentFree
import org.springframework.data.jpa.repository.JpaRepository

interface CommentFreeRepository : JpaRepository<CommentFree, Long> {
}