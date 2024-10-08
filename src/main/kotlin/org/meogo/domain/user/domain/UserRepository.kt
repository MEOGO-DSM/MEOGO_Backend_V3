package org.meogo.domain.user.domain

import org.springframework.data.repository.Repository
import java.util.UUID

interface UserRepository : Repository<User, UUID> {
    fun save(entity: User): User

    fun findById(id: UUID): User?

    fun findByAccountId(accountId: String): User

    fun existsByAccountId(accountId: String): Boolean
}
