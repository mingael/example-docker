package com.api.auth.service

import com.api.auth.controller.request.RegisterRequest
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val accountService: AccountService
) {

    fun register(dto: RegisterRequest) {
        accountService.createAccount(dto.name)
    }

}