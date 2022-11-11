package com.dev.home.account.controller

import com.dev.home.account.controller.request.AccountRequest
import com.dev.home.account.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/register")
    fun register(@RequestBody dto: AccountRequest): ResponseEntity<Any> {
        accountService.register(dto)
        return ResponseEntity.ok("OK")
    }

    @GetMapping("")
    fun getUsers(): ResponseEntity<Any> {
        val data = accountService.getUsers()
        return ResponseEntity.ok(data)
    }

}