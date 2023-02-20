package com.example.swagger.account.controller

import com.example.swagger.account.dto.AccountDTO
import com.example.swagger.account.service.AccountService
import com.example.swagger.constant.ApiEntity
import com.example.swagger.util.ApiUtils
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.annotations.ParameterObject
import org.springdoc.core.converters.models.Pageable
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
@Tag(name = "사용자 API")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping
    @Operation(summary = "사용자 목록 조회 - pageable")
    fun getUser1(pageable: Pageable): ApiEntity<List<AccountDTO.Response>> {
        val account = accountService.getUsers(pageable)
        return ApiUtils.success(account)
    }

    @GetMapping("/p1")
    @Operation(summary = "사용자 목록 조회 - pageable ParameterObject")
    fun getUser2(@ParameterObject pageable: Pageable): ApiEntity<List<AccountDTO.Response>> {
        val account = accountService.getUsers(pageable)
        return ApiUtils.success(account)
    }

    @GetMapping("/p2")
    @Operation(summary = "사용자 목록 조회 - pageable PageableAsQueryParam")
    @PageableAsQueryParam
    fun getUser3(@Parameter(hidden = true) pageable: Pageable): ApiEntity<List<AccountDTO.Response>> {
        val account = accountService.getUsers(pageable)
        return ApiUtils.success(account)
    }

    @PostMapping
    fun createUser(@ParameterObject dto: AccountDTO.Response): ApiEntity<Any> {
        accountService.createUser(dto)
        return ApiUtils.success()
    }

}