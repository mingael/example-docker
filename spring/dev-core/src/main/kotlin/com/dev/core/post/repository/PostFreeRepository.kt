package com.dev.core.post.repository

import com.dev.core.post.domain.PostFree
import org.springframework.data.jpa.repository.JpaRepository

interface PostFreeRepository : JpaRepository<PostFree, Long> {
}