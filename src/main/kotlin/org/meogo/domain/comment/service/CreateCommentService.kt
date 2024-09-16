package org.meogo.domain.comment.service

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.presentation.dto.request.CommentRequest
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.exception.PostNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateCommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: CommentRequest) {
        val user = userFacade.currentUser()!!
        val post = postRepository.findById(request.postId) ?: throw PostNotFoundException

        commentRepository.save(
            Comment(
                content = request.content,
                date = LocalDateTime.now(),
                post = post,
                user = user
            )
        )
    }
}
