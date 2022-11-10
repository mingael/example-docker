package com.dev.home.account.controller

import com.dev.home.account.controller.request.AccountRequest
import com.dev.home.account.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AuthController(
    private val accountService: AccountService
) {

    @PostMapping("/register")
    fun register(@RequestBody dto: AccountRequest): ResponseEntity<Any> {
        accountService.register(dto)
        return ResponseEntity.ok("OK")
    }

}