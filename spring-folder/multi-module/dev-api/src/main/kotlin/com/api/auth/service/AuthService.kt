package com.api.auth.service

import com.api.auth.controller.request.RegisterRequest
import com.api.common.provider.GoogleApiProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthService(
    private val accountService: AccountService,
    private val googleApiProvider: GoogleApiProvider
) {

    @Transactional
    fun register(dto: RegisterRequest) {
        accountService.createAccount(dto.name)
    }

    fun loginGoogle(code: String) {
        // 토큰 받아서
        val token = googleApiProvider.getToken(code)
        // 사용자 정보 조회
        val info = googleApiProvider.getUserInfo(token.accessToken)
        // 사용자 정보 처리
    }

}