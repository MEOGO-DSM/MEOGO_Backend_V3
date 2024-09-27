package org.meogo.domain.question.presentation.dto.response

import org.meogo.domain.comment.presentation.dto.response.CommentListResponse
import org.meogo.domain.question.enum.QuestionType

data class QuestionDetailResponse(
    val id: Long,
    val name: String,
    val date: String,
    val content: String,
    val tag: QuestionType,
    val comments: CommentListResponse
)
