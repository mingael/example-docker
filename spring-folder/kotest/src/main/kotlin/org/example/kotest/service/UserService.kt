package org.example.kotest.service

interface UserService {

    fun login(email: String, password: String): String

    fun getSession(email: String): String

}