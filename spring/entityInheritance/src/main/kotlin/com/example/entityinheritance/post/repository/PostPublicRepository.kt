package com.example.entityinheritance.post.repository

import com.example.entityinheritance.post.domain.PostPublic
import org.springframework.data.jpa.repository.JpaRepository

interface PostPublicRepository : JpaRepository<PostPublic, Long>