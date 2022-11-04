package com.example.entityinheritance.comment.repository

import com.example.entityinheritance.comment.domain.core.BaseComment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<BaseComment, Long>, CommentRepositoryCustom