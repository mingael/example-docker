package com.example.entityinheritance.post.repository

import com.example.entityinheritance.post.vo.PostVO

interface PostRepositoryCustom {

    fun findPost(postType: String): List<PostVO>

}