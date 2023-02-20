package com.example.swagger.account.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 정보")
class AccountDTO {

    data class Response(
        @field:Schema(description = "일련번호")
        val id: Long,
        @field:Schema(description = "성명")
        val name: String,
        @field:Schema(description = "나이")
        val age: Int
    )

}
