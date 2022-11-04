package com.example.entityinheritance.post.repository

import com.example.entityinheritance.common.config.QueryDslRepositoryCustom
import com.example.entityinheritance.post.domain.QPostPrivate.postPrivate
import com.example.entityinheritance.post.domain.QPostPublic.postPublic
import com.example.entityinheritance.post.domain.core.BasePost
import com.example.entityinheritance.post.domain.core.QBasePost.basePost
import com.example.entityinheritance.post.vo.PostVO
import com.querydsl.core.types.Projections

class PostRepositoryCustomImpl : QueryDslRepositoryCustom(BasePost::class.java), PostRepositoryCustom {

    override fun findPost(postType: String): List<PostVO> {
        val q = query
            .select(
                Projections.fields(
                    PostVO::class.java,
                    basePost.id,
                    basePost.title,
                    basePost.content
                )
            )
            .from(basePost)
        if (postType == "public") q.innerJoin(postPublic).on(basePost.id.eq(postPublic.id))
        if (postType == "private") q.innerJoin(postPrivate).on(basePost.id.eq(postPrivate.id))
        return q.fetch()
    }

}