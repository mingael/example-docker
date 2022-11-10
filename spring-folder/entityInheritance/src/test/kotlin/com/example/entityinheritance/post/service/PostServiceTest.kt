package com.example.entityinheritance.post.service

import com.example.entityinheritance.post.controller.request.PostPrivateRequest
import com.example.entityinheritance.post.controller.request.PostPublicRequest
import com.example.entityinheritance.post.domain.enum.PostType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PostServiceTest @Autowired constructor(
    private val postService: PostService,
    private val privateService: PostPrivateService,
    private val publicService: PostPublicService
) {

    @Test
    fun postTest() {
        privateService.createPost(PostPrivateRequest(
            title = "비공개 글",
            content = "비공개 ${System.currentTimeMillis()}",
            category = "lol"
        ))

        publicService.createPost(PostPublicRequest(
            title = "공개 글",
            content = "공개 ${System.currentTimeMillis()}",
            tag = "2"
        ))

        val list = postService.getPost(PostType.PRIVATE)
        println("list: $list")
    }

}