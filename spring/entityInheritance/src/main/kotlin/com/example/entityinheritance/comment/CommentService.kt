package com.example.entityinheritance.comment

import com.example.entityinheritance.comment.controller.response.CommentResponse
import com.example.entityinheritance.comment.repository.CommentRepository
import com.example.entityinheritance.post.domain.enum.PostType
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {

    fun getComments(postType: PostType): CommentResponse {
        val vo = commentRepository.findComment(postType)
        return CommentResponse.of(vo)
    }

}