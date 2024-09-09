package org.meogo.domain.post.domain

import org.springframework.data.repository.Repository

interface PostRepository : Repository<Post, Long> {

    fun save(post: Post)

    fun findAll(): List<Post>
}
