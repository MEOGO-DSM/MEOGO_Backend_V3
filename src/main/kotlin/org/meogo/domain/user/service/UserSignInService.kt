package org.meogo.domain.user.service

import org.meogo.domain.user.exception.PasswordMismatchException
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.presentation.dto.request.UserSignInRequest
import org.meogo.domain.user.repository.UserRepository
import org.meogo.global.jwt.JwtTokenProvider
import org.meogo.global.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignInService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun execute(request: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UserNotFoundException

        if (!passwordEncoder.matches(request.password, user.password)) throw PasswordMismatchException

        return jwtTokenProvider.getToken(user.accountId)
    }
}
