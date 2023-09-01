package org.example.kotest.service

import org.example.kotest.exception.PasswordFailException
import org.example.kotest.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun login(email: String, password: String): String {
        // 계정 조회
        val user = userRepository.findByEmail(email) ?: throw RuntimeException("email? $email")
        // 비밀번호 확인
        if (user.password != password) throw PasswordFailException()
        return getSession(email)
    }

    override fun getSession(email: String): String {
        return "session-key"
    }

}