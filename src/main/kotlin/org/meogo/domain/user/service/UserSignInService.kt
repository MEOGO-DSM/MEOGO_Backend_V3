package org.meogo.domain.user.service

import org.meogo.domain.user.domain.UserRepository
import org.meogo.domain.user.exception.PasswordMismatchException
import org.meogo.domain.user.presentation.dto.request.UserSignInRequest
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

        if (!passwordEncoder.matches(request.password, user.password)) throw PasswordMismatchException

        userRepository.save(
            user.updateDeviceToken(
                request.deviceToken
            )
        )
        return jwtTokenProvider.getToken(user.accountId)
    }
}
