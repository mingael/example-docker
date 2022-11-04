package com.example.entityinheritance.comment.repository

import com.example.entityinheritance.comment.domain.QCommentPrivate
import com.example.entityinheritance.comment.domain.QCommentPublic
import com.example.entityinheritance.comment.domain.core.BaseComment
import com.example.entityinheritance.comment.vo.CommentVO
import com.example.entityinheritance.common.config.QueryDslRepositoryCustom
import com.example.entityinheritance.post.domain.enum.PostType
import com.querydsl.core.types.Projections

class CommentRepositoryCustomImpl : QueryDslRepositoryCustom(BaseComment::class.java), CommentRepositoryCustom {

    override fun findComment(postType: PostType): List<CommentVO> {
        val comment = getBaseComment(postType)
        return query
            .select(
                Projections.fields(
                    CommentVO::class.java,
                    comment.id,
                    comment.parentId,
                    comment.message
                )
            )
            .from(comment)
            .fetch()
    }

    private fun getBaseComment(postType: PostType) = when (postType) {
        PostType.PUBLIC -> QCommentPublic.commentPublic._super
        PostType.PRIVATE -> QCommentPrivate.commentPrivate._super
    }

}