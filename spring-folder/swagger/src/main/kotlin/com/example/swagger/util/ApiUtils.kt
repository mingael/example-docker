package com.example.swagger.util

import com.example.swagger.constant.ApiResult
import org.springframework.http.ResponseEntity

class ApiUtils {

    companion object {

        fun <T> success(response: T? = null): ResponseEntity<ApiResult<T>> {
            return ResponseEntity.ok(
                ApiResult(
                    result = true,
                    payload = response
                )
            )
        }

        fun <T> failure(message: String? = null): ResponseEntity<ApiResult<T>> {
            return ResponseEntity.ok(
                ApiResult(
                    result = false,
                    message = message
                )
            )
        }

    }

}