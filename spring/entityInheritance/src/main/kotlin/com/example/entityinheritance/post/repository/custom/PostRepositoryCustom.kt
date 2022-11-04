package com.example.entityinheritance.post.repository.custom

import com.example.entityinheritance.post.domain.enum.PostType
import com.example.entityinheritance.post.vo.PostVO

interface PostRepositoryCustom {

    fun findPost(postType: PostType): List<PostVO>

}