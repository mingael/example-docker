package com.example.swagger.auth.service

import com.example.swagger.auth.dto.LoginDTO
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun login(dto: LoginDTO.Request): LoginDTO.Response {
        return LoginDTO.Response(
            name = "a",
            age = 19
        )
    }

}