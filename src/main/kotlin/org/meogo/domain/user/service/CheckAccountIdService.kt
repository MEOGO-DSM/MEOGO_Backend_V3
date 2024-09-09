package org.meogo.domain.user.service

import org.meogo.domain.user.domain.UserRepository
import org.meogo.domain.user.presentation.dto.request.UserCheckRequest
import org.springframework.stereotype.Service

@Service
class CheckAccountIdService(
    private val userRepository: UserRepository
) {
    fun execute(request: UserCheckRequest): Boolean {
        return userRepository.existsByAccountId(request.accountId)
    }
}
