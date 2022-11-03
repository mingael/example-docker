package com.example.swagger.account.controller

import com.example.swagger.account.dto.AccountDTO
import com.example.swagger.account.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/{accountId}")
    fun getUser(@PathVariable accountId: Long): ResponseEntity<AccountDTO> {
        val account = accountService.getUser(accountId)
        return ResponseEntity.ok(account)
    }

}