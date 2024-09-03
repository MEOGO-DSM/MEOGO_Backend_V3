package org.meogo.domain.user.repository

import org.meogo.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun save(entity: User): User

    fun findByAccountId(accountId: String): User?

    fun existsByAccountId(accountId: String): Boolean
}
