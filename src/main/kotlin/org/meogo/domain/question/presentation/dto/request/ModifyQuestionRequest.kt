package org.meogo.domain.question.presentation.dto.request

import org.meogo.domain.question.enum.QuestionType
import javax.validation.constraints.Size

data class ModifyQuestionRequest(
    @field: Size(min = 1, max = 100)
    val content: String,
    val questionType: QuestionType
)
