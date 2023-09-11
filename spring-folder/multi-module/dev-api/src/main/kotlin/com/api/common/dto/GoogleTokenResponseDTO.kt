package com.api.common.dto

import com.google.gson.annotations.SerializedName

data class GoogleTokenResponseDTO(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)
