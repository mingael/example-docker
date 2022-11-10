package com.example.swagger.account.service

import com.example.swagger.account.dto.AccountDTO
import org.springframework.stereotype.Service

@Service
class AccountService {

    fun getUser(id: Long): AccountDTO {
        return AccountDTO(id)
    }

}