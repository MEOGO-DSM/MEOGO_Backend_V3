package org.meogo.domain.user.service

import org.meogo.domain.user.domain.User
import org.meogo.domain.user.domain.UserRepository
import org.meogo.domain.user.domain.UserRole
import org.meogo.domain.user.presentation.dto.request.UserSignUpRequest
import org.meogo.global.jwt.JwtTokenProvider
import org.meogo.global.jwt.dto.TokenResponse
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSignUpService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val fileUtil: FileUtil,
    @Value("\${cloud.aws.s3.default-image}")
    private val defaultImage: String

) {
    fun execute(request: UserSignUpRequest): TokenResponse {
        val image = fileUtil.generateObjectUrl(defaultImage, Path.USER)

        val user = User(
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            enrolledSchool = request.enrolledSchool?.toInt(),
            role = UserRole.USER,
            profile = image
        )
        userRepository.save(user)

        return jwtTokenProvider.getToken(user.accountId)
    }
}
