package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMatchService(
    private val userFacade: UserFacade,
    private val reviewRepository: ReviewRepository
) {

    @Transactional(readOnly = true)
    fun execute(schoolId: Int): Boolean {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        if (reviewRepository.existsByUserId(user.id!!)) return false
        return user.enrolledSchool == schoolId
    }
}
