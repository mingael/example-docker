package com.api.auth.controller

import com.api.auth.controller.request.RegisterRequest
import com.api.auth.service.AuthService
import com.api.common.provider.GoogleApiProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
    private val googleApiProvider: GoogleApiProvider
) {

    @PostMapping("/register")
    fun register(dto: RegisterRequest): ResponseEntity<Any> {
        authService.register(dto)
        return ResponseEntity.ok(true)
    }

    /**
     * 구글 로그인창 호출
     * http://localhost:8080/api/auth/login/google-auth
     */
    @GetMapping("/login/google-auth")
    fun redirectGoogleAuth(request: HttpServletRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        headers.location = URI.create(googleApiProvider.getGoogleAuthUrl())
        return ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY)
    }

    /**
     * 구글 로그인 후 호출됨
     */
    @GetMapping("/login/google")
    fun loginGoogle(
        request: HttpServletRequest,
        @RequestParam code: String?,
        @RequestParam error: String?
    ): ResponseEntity<Any> {
        return code?.let {
            try {
                authService.loginGoogle(code)
                ResponseEntity.ok(HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity.ok(HttpStatus.BAD_REQUEST)
            }
        } ?: ResponseEntity.ok(HttpStatus.BAD_REQUEST)
    }

}