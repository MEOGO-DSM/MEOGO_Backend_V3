package org.meogo.domain.bookmark.domain

import org.meogo.domain.user.domain.User
import org.meogo.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Bookmark(
    id: UUID? = null,

    val schoolId: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : BaseUUIDEntity(id)
