package org.meogo.domain.question.service

import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.question.enum.QuestionType
import org.meogo.domain.question.presentation.dto.response.QueryQuestionResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryTypeQuestionService(
    private val questionRepository: QuestionRepository
) {

    @Transactional(readOnly = true)
    fun execute(schoolId: Int, questionType: QuestionType): List<QueryQuestionResponse> {
        val questions = questionRepository.findBySchoolIdAndQuestionType(schoolId, questionType)

        return questions.map { question ->
            QueryQuestionResponse(
                id = question.id,
                content = question.content,
                date = question.format(question.date),
                questionType = question.questionType,
                name = question.user.accountId
            )
        }
    }
}
