package org.meogo.domain.question.service

import org.meogo.domain.question.domain.Question
import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.question.presentation.dto.request.QuestionRequest
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateQuestionService(
    private val questionRepository: QuestionRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: QuestionRequest) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        questionRepository.save(
            Question(
                content = request.content,
                date = LocalDateTime.now(),
                schoolId = request.schoolId,
                questionType = request.questionType,
                user = user
            )
        )
    }
}
