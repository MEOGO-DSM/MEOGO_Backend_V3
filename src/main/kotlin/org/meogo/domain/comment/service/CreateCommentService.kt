package org.meogo.domain.comment.service

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.enum.CommentType
import org.meogo.domain.comment.exception.CommentNotFoundException
import org.meogo.domain.comment.presentation.dto.request.CommentRequest
import org.meogo.domain.post.domain.Post
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.exception.PostNotFoundException
import org.meogo.domain.question.domain.Question
import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.question.exception.QuestionNotFoundException
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateCommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
    private val questionRepository: QuestionRepository
) {

    @Transactional
    fun execute(request: CommentRequest) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        val rightEntity = when (request.commentType) {
            CommentType.POST -> postRepository.findById(request.id) ?: throw PostNotFoundException
            CommentType.QUESTION -> questionRepository.findById(request.id) ?: throw QuestionNotFoundException
            CommentType.COMMENT -> commentRepository.findById(request.id) ?: throw CommentNotFoundException
        }

        // 댓글 저장
        val comment = Comment(
            content = request.content,
            date = LocalDateTime.now(),
            user = user,
            commentType = request.commentType,
            post = if (request.commentType == CommentType.POST) rightEntity as Post else null,
            question = if (request.commentType == CommentType.QUESTION) rightEntity as Question else null,
            comment = if (request.commentType == CommentType.COMMENT) rightEntity as Comment else null
        )

        commentRepository.save(comment)
    }
}
