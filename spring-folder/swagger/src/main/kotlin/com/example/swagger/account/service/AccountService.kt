package com.example.swagger.account.service

import com.example.swagger.account.dto.AccountDTO
import org.springdoc.core.converters.models.Pageable
import org.springframework.stereotype.Service

@Service
class AccountService {

    fun getUsers(pageable: Pageable): List<AccountDTO.Response> {
        return listOf()
    }

    fun getUser(id: Long): AccountDTO.Response {
        return AccountDTO.Response(id, "", 20)
    }

    fun createUser(dto: AccountDTO.Response) {

    }

}