package com.example.entityinheritance.post.repository

import com.example.entityinheritance.post.domain.PostPrivate
import org.springframework.data.jpa.repository.JpaRepository

interface PostPrivateRepository : JpaRepository<PostPrivate, Long>