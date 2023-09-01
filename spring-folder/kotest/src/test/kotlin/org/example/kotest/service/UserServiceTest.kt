package org.example.kotest.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.kotest.domain.User
import org.example.kotest.exception.PasswordFailException
import org.example.kotest.repository.UserRepository

internal class UserServiceTest : BehaviorSpec({
    // UserService 의존성 객체 선언
    val userRepository = mockk<UserRepository>()
    // 테스트 데이터 선언
    var email: String
    var password: String

    Given("로그인 테스트") {
        email = "test@gmail.com"
        password = "test-password"

        // UserService에 의존성 객체 주입
        val service = withContext(Dispatchers.IO) {
            UserServiceImpl(userRepository)
        }

        When("올바른 계정을 입력하면") {
            // 계정 조회 결과값을 임의로 설정
            every { userRepository.findByEmail(any()) } returns user
            Then("로그인 성공") {
                val result = service.login(email = email, password = password)
                // 결과값 검증
                result shouldNotBe null
                result shouldBe "session-key"
            }
        }

        When("올바르지 않은 계정을 입력하면") {
            And("존재하지 않는 이메일을 입력하면") {
                every { userRepository.findByEmail(any()) } returns null

                Then("예외가 발생한다") {
                    val result = shouldThrow<RuntimeException> {
                        service.login(email = email, password = password)
                    }
                    result.message shouldBe "email? $email"
                }
            }
            And("비밀번호를 틀리게 입력하면") {
                password = "test-password-1"
                every { userRepository.findByEmail(any()) } returns user

                Then("예외가 발생한다") {
                    shouldThrow<PasswordFailException> {
                        service.login(email = email, password = password)
                    }
                }
            }
        }
    }
}) {
    companion object {
        val user = User(
            id = 1L,
            email = "test@gmail.com",
            password = "test-password"
        )
    }
}