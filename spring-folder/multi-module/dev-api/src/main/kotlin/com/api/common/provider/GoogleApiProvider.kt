package com.api.common.provider

import com.api.common.dto.GoogleTokenResponseDTO
import com.api.common.dto.GoogleUserInfoResponseDTO
import com.api.common.exception.GoogleApiException
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.net.URLEncoder

@Component
class GoogleApiProvider(
    @Value("\${google.client.id}") private val clientId: String,
    @Value("\${google.client.secret}") private val clientSecret: String,
    @Value("\${google.auth.url}") private val authUrl: String,
    @Value("\${google.auth.redirect-url}") private val redirectUrl: String
) {

    /**
     * 구글 로그인 화면 URL
     */
    fun getGoogleAuthUrl(): String {
        return "https://accounts.google.com/o/oauth2/v2/auth?" +
            "scope=${URLEncoder.encode("openid email profile", Charsets.UTF_8)}" +
            "&access_type=offline" +
            "&response_type=code" +
            "&redirect_uri=$redirectUrl" +
            "&client_id=$clientId"
    }

    /**
     * 토큰 받기
     * application/x-www-form-urlencoded
     */
    fun getToken(code: String): GoogleTokenResponseDTO {
        val url = "$authUrl/token"
        val param = LinkedMultiValueMap<String, String>()
        param["code"] = code
        param["client_id"] = clientId
        param["client_secret"] = clientSecret
        param["redirect_uri"] = redirectUrl
        param["grant_type"] = "authorization_code"

        val response = RestTemplate().postForObject(url, param, String::class.java) ?: throw GoogleApiException("token")
        return Gson().fromJson(response, GoogleTokenResponseDTO::class.java)
    }

    /**
     * 사용자 정보 조회
     */
    fun getUserInfo(accessToken: String): GoogleUserInfoResponseDTO {
        val url = "https://openidconnect.googleapis.com/v1/userinfo?access_token=$accessToken"

        val headers = HttpHeaders()
        headers.setBearerAuth(accessToken)

        val request = HttpEntity<MultiValueMap<String, String>>(LinkedMultiValueMap(), headers)

        val response = RestTemplate().exchange(url, HttpMethod.POST, request, String::class.java)
        return if (response.statusCode == HttpStatus.OK)
            Gson().fromJson(response.body, GoogleUserInfoResponseDTO::class.java)
        else throw GoogleApiException("user info")
    }

    fun cancelToken(accessToken: String) {
        val url = "$authUrl/revoke"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val request = HttpEntity<String>(headers)
        RestTemplate().postForEntity(url, request, String::class.java)
    }
}