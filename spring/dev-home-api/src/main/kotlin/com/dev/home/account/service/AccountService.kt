package com.dev.home.account.service

import com.dev.core.account.domain.Account
import com.dev.core.account.repository.AccountRepository
import com.dev.home.account.controller.request.AccountRequest
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.LockModeType

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    fun register(dto: AccountRequest): Account? {
        if (accountRepository.findByEmail(dto.email) != null) return null
        return accountRepository.save(
            Account(
                name = dto.name,
                nickname = dto.nickname,
                email = dto.email,
                password = dto.password
            )
        )
    }

    fun getUsers(): List<Account> {
        return accountRepository.findAll()
    }

}