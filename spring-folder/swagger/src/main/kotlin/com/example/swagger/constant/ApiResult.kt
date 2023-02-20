package com.example.swagger.constant

import org.springframework.http.ResponseEntity

data class ApiResult<T>(
    val result: Boolean,
    val payload: T? = null,
    val message: String? = null
)

typealias ApiEntity<T> = ResponseEntity<ApiResult<T>>