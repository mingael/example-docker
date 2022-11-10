package com.example.entityinheritance.comment.service

import com.example.entityinheritance.comment.CommentService
import com.example.entityinheritance.post.domain.enum.PostType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CommentServiceTest @Autowired constructor(
    private val commentService: CommentService
) {

    @Test
    fun test() {
        commentService.getComments(PostType.PRIVATE)
    }

}