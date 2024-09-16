package org.meogo.domain.comment.domain

import org.springframework.data.repository.Repository

interface CommentRepository : Repository<Comment, Long> {
    fun save(comment: Comment)

    fun findById(id: Long): Comment?
    fun deleteById(id: Long)
}
