package org.meogo.domain.bookmark.domain

import org.meogo.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BookmarkRepository : JpaRepository<Bookmark, UUID> {
    fun findAllByUser(user: User): List<Bookmark>?

    fun findBySchoolIdAndUser(schoolId: Int, user: User): Bookmark?

    fun existsBySchoolIdAndUser(schoolId: Int, user: User): Boolean
}
