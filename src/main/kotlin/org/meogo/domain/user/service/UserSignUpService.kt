package org.meogo.domain.user.service

import org.meogo.domain.user.domain.User
import org.meogo.domain.user.domain.UserRole
import org.meogo.domain.user.presentation.dto.request.UserSignUpRequest
import org.meogo.domain.user.repository.UserRepository
import org.meogo.global.jwt.JwtTokenProvider
import org.meogo.global.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSignUpService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder

) {
    fun execute(request: UserSignUpRequest): TokenResponse {
        val user = User(
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            enrolledSchool = request.enrolledSchool?.toInt(),
            role = UserRole.USER
        )
        userRepository.save(user)

        return jwtTokenProvider.getToken(user.accountId)
    }
}
