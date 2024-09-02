package org.meogo.domain.user.domain

import org.meogo.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class User(
    id: UUID? = null,

    @Column(nullable = false, length = 4)
    val name: String,

    @Column(name = "account_id", nullable = false, length = 15, unique = true)
    val accountId: String,

    val password: String,

    @Column(name = "enrolled_school", nullable = true)
    val enrolledSchool: Int? = 0,

    val profile: String? = null,

    @Enumerated(EnumType.STRING)
    val role: UserRole
) : BaseUUIDEntity(id)
