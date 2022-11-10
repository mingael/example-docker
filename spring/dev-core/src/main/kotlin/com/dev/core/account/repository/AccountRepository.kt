package com.dev.core.account.repository

import com.dev.core.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByEmail(email: String): Account?

}