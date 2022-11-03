package com.example.swagger.auth.dto

class LoginDTO {

    data class Request(
        val email: String,
        val password: String
    )

    data class Response(
        val name: String,
        val age: Int
    )

}
