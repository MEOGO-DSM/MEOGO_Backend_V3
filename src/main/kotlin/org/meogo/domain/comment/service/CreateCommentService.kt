package org.meogo.domain.comment.service

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.enum.CommentType
import org.meogo.domain.comment.presentation.dto.request.CommentRequest
import org.meogo.domain.post.domain.Post
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.question.domain.Question
import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.utill.FcmUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateCommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
    private val questionRepository: QuestionRepository,
    private val fcmUtil: FcmUtil
) {

    @Transactional
    fun execute(request: CommentRequest) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        val rightEntity: Any = when (request.commentType) {
            CommentType.POST -> postRepository.findById(request.id)
            CommentType.QUESTION -> questionRepository.findById(request.id)
            CommentType.COMMENT -> commentRepository.findById(request.id)
        }

        val comment = Comment(
            content = request.content,
            date = LocalDateTime.now(),
            user = user,
            commentType = request.commentType,
            post = if (request.commentType == CommentType.POST) rightEntity as Post? else null,
            question = if (request.commentType == CommentType.QUESTION) rightEntity as Question? else null,
            comment = if (request.commentType == CommentType.COMMENT) rightEntity as Comment? else null
        )

        commentRepository.save(comment)

        val list: List<String> = emptyList()
        fcmUtil.sendMessage(list.plus(comment.post!!.user.deviceToken!!), rightEntity.toString(), "새로운 댓글이 달렸습니다")
    }
}
