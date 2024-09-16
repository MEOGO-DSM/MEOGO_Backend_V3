package org.meogo.domain.comment.service

import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.exception.CommentNotFoundException
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteCommentService(
    private val commentRepository: CommentRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(commentId: Long) {
        val user = userFacade.currentUser()!!
        val comment = commentRepository.findById(commentId)?: throw CommentNotFoundException

        if(user.id != comment.user.id)
            throw UserMisMatchException

        commentRepository.deleteById(comment.id)
    }
}