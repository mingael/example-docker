package com.example.swagger.auth.controller

import com.example.swagger.auth.dto.LoginDTO
import com.example.swagger.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증 API")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호를 입력받아 로그인을 처리합니다.")
    fun login(dto: LoginDTO.Request): ResponseEntity<Any> {
        val data = authService.login(dto)
        return ResponseEntity.ok(data)
    }

}