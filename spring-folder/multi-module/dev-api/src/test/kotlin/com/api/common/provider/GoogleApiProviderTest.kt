package com.api.common.provider

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GoogleApiProviderTest(
    @Autowired
    private val googleApiProvider: GoogleApiProvider
) {

    @Test
    fun getGoogleAuthUrlTest() {
        val url = googleApiProvider.getGoogleAuthUrl()
        println(url)
    }
}