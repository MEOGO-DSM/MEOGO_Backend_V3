package org.meogo.domain.user.service

import org.meogo.domain.user.presentation.dto.request.UserCheckRequest
import org.meogo.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CheckAccountIdService(
    private val userRepository: UserRepository
) {
    fun execute(request: UserCheckRequest): Boolean {
        return userRepository.existsByAccountId(request.accountId)
    }
}
