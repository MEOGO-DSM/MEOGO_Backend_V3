package org.meogo.domain.bookmark

import org.meogo.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BookmarkRepository : JpaRepository<Bookmark, UUID> {
    fun findAllBySchoolId(schoolId: Int): List<Bookmark>?

    fun existsBySchoolIdAndUser(schoolId: Int, user: User): Boolean

    fun deleteBySchoolIdAndUser(schoolId: Int, user: User)
}
