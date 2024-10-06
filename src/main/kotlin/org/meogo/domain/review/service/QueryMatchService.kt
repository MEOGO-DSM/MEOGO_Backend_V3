package org.meogo.domain.review.service

import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMatchService(
    private val userFacade: UserFacade
) {

    @Transactional(readOnly = true)
    fun execute(schoolId: Int): Boolean {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        return user.enrolledSchool == schoolId
    }
}
