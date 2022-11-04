package com.example.entityinheritance.post.repository

import com.example.entityinheritance.post.domain.core.BasePost
import com.example.entityinheritance.post.repository.custom.PostRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<BasePost, Long>, PostRepositoryCustom {
}