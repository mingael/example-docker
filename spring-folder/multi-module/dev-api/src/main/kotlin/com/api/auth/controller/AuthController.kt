package com.api.auth.controller

import com.api.auth.controller.request.RegisterRequest
import com.api.auth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(dto: RegisterRequest): ResponseEntity<Any> {
        authService.register(dto)
        return ResponseEntity.ok(true)
    }

}