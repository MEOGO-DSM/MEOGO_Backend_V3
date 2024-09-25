package org.meogo.domain.post.domain

import org.springframework.data.repository.Repository
import java.util.UUID

interface PostRepository : Repository<Post, Long> {

    fun save(post: Post)

    fun deleteById(id: Long)

    fun findAll(): List<Post>

    fun findById(id: Long): Post

    fun findByUserId(userId: UUID): List<Post>

    fun findBySchoolId(schoolId: Int): List<Post>
}
