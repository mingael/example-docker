package com.api.auth.service

import com.api.auth.controller.request.RegisterRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val accountService: AccountService
) {

    @Transactional
    fun register(dto: RegisterRequest) {
        accountService.createAccount(dto.name)
    }

}