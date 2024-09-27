package org.meogo.domain.question.presentation.dto.response

import org.meogo.domain.question.enum.QuestionType

data class QueryQuestionResponse(
    val id: Long,
    val content: String,
    val date: String,
    val questionType: QuestionType,
    val accountId: String
)
