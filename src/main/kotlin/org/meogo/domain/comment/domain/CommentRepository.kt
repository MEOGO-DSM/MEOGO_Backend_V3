package org.meogo.domain.comment.domain

import org.meogo.domain.post.domain.Post
import org.springframework.data.repository.Repository

interface CommentRepository : Repository<Comment, Long> {
    fun save(comment: Comment)

    fun findById(id: Long): Comment

    fun deleteById(id: Long)

    fun findAllByPost(post: Post): List<Comment>

    fun findAllByComment(comment: Comment): List<Comment>
}
