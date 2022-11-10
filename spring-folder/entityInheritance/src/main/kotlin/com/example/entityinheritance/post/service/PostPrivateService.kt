package com.example.entityinheritance.post.service

import com.example.entityinheritance.post.controller.request.PostPrivateRequest
import com.example.entityinheritance.post.repository.PostPrivateRepository
import org.springframework.stereotype.Service

@Service
class PostPrivateService(
    private val privateRepository: PostPrivateRepository,
) {

    fun createPost(dto: PostPrivateRequest) {
        privateRepository.save(dto.toEntity())
    }

}