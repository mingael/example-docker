package com.dev.home.account.controller

import com.google.gson.Gson
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

@SpringBootTest
@AutoConfigureMockMvc
internal class AuthControllerTest @Autowired constructor(
    private val mvc: MockMvc
) {

    @Test
    @DisplayName("create account multi thread test")
    fun creatAccount() {
        val param = mapOf(
            "name" to "a",
            "nickname" to "b",
            "email" to "c",
            "password" to "pw"
        )
        for (idx in 1..10) {
            mvc.perform(
                post("/api/account/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Gson().toJson(param))
            )
                .andDo(MockMvcResultHandlers.print())
        }
    }

}