package com.example.entityinheritance.post.service

import com.example.entityinheritance.post.domain.enum.PostType
import com.example.entityinheritance.post.vo.PostVO
import com.example.entityinheritance.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {

    fun getPost(postType: PostType): List<PostVO> {
        return postRepository.findPost(postType)
    }

}