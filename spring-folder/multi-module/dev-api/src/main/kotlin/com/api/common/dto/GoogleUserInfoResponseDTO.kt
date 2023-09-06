package com.api.common.dto

import com.google.gson.annotations.SerializedName

data class GoogleUserInfoResponseDTO(
    val sub: String,
    val name: String,
    @SerializedName("given_name")
    val givenName: String,
    val picture: String,
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: String,
    val locale: String
)
