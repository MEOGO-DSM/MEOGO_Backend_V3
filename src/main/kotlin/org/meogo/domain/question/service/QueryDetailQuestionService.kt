package org.meogo.domain.question.service

import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.presentation.dto.response.CommentListResponse
import org.meogo.domain.comment.service.CommentService
import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.question.presentation.dto.response.QuestionDetailResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryDetailQuestionService(
    private val questionRepository: QuestionRepository,
    private val commentRepository: CommentRepository,
    private val commentService: CommentService
) {
    @Transactional(readOnly = true)
    fun execute(questionId: Long): QuestionDetailResponse {
        val question = questionRepository.findById(questionId)

        val basicComments = commentRepository.findAllByQuestion(question)

        val replies = commentService.getCommentResponses(basicComments)

        val contentListResponse = CommentListResponse(
            count = replies.size + basicComments.size,
            commentList = replies.sortedByDescending { it.date }
        )

        return QuestionDetailResponse(
            id = questionId,
            name = question.user.accountId,
            date = question.format(question.date),
            content = question.content,
            tag = question.questionType,
            comments = contentListResponse
        )
    }
}
