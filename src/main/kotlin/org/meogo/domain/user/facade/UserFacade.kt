package org.meogo.domain.user.facade

import org.meogo.domain.user.domain.User
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun currentUser(): User? {
        val accountId = SecurityContextHolder.getContext().authentication.name
        return accountId?.let { getUserByAccountId(it) }
    }

    fun getUserByAccountId(accountId: String): User =
        userRepository.findByAccountId(accountId) ?: throw UserNotFoundException

    fun getUserById(id: UUID): User =
        userRepository.findById(id).orElseThrow { UserNotFoundException }
}
