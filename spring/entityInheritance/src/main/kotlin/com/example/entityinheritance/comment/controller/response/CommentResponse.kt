package com.example.entityinheritance.comment.controller.response

import com.example.entityinheritance.comment.vo.CommentVO

data class CommentResponse(
    val list: List<CommentDTO>
) {
    data class CommentDTO(
        val id: Long,
        val parentId: Long?,
        val message: String
    )

    companion object {
        fun of(vos: List<CommentVO>) = CommentResponse(
            list = vos.map {
                CommentDTO(
                    id = it.id,
                    parentId = it.parentId,
                    message = it.message
                )
            }
        )
    }
}
