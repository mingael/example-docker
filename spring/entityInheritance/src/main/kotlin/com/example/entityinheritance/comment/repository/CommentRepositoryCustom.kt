package com.example.entityinheritance.comment.repository

import com.example.entityinheritance.comment.vo.CommentVO
import com.example.entityinheritance.post.domain.enum.PostType

interface CommentRepositoryCustom {

    fun findComment(postType: PostType): List<CommentVO>

}