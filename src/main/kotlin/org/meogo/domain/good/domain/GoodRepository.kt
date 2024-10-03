package org.meogo.domain.good.domain

import org.meogo.domain.post.domain.Post
import org.meogo.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface GoodRepository : JpaRepository<Good, UUID> {

    fun findAllByUser(user: User): List<Good>?

    fun findByUserAndPost(user: User, post: Post): Good?

    fun existsByUserAndPost(user: User, post: Post): Boolean
}
