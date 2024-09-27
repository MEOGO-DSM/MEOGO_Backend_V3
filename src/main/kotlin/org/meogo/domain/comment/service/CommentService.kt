package org.meogo.domain.comment.service

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.presentation.dto.response.CommentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    @Transactional(readOnly = true)
    fun getCommentResponses(basicComments: List<Comment>): List<CommentResponse> {
        return basicComments.map { basicComment ->
            val replies = commentRepository.findAllByComment(basicComment)
            CommentResponse(basicComment, replies.map { CommentResponse(it) })
        }
    }
}
