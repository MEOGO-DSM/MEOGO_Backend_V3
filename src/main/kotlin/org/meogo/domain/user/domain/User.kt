package org.meogo.domain.user.domain

import org.meogo.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
data class User(
    override val id: UUID,

    @Column(nullable = false, length = 4)
    var name: String,

    @Column(name = "account_id", nullable = false, length = 15, unique = true)
    val accountId: String,

    val password: String,

    @Column(name = "enrolled_school", nullable = true)
    var enrolledSchool: Int? = 0,

    var profile: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @Column(name = "device_token", nullable = true)
    val deviceToken: String? = null
) : BaseUUIDEntity(id) {
    fun updateProfile(name: String, enrolledSchool: Int, profile: String): User {
        this.name = name
        this.enrolledSchool = enrolledSchool
        this.profile = profile
        return this
    }
}
