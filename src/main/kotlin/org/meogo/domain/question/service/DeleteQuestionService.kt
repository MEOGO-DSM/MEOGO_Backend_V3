package org.meogo.domain.question.service

import org.meogo.domain.question.domain.QuestionRepository
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteQuestionService(
    private val questionRepository: QuestionRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(questionId: Long) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val question = questionRepository.findById(questionId)

        if (user != question.user) throw UserMisMatchException

        questionRepository.deleteById(question.id)
    }
}
