package com.example.entityinheritance.post.service

import com.example.entityinheritance.post.controller.request.PostPublicRequest
import com.example.entityinheritance.post.repository.PostPublicRepository
import org.springframework.stereotype.Service

@Service
class PostPublicService(
    private val publicRepository: PostPublicRepository
) {

    fun createPost(dto: PostPublicRequest) {
        publicRepository.save(dto.toEntity())
    }

}