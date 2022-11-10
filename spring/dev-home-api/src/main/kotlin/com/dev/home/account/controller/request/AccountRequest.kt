package com.dev.home.account.controller.request

data class AccountRequest(
    val name: String,
    val nickname: String,
    val email: String,
    val password: String
)