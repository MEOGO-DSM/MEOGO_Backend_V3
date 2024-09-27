package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.exception.ReviewNotFoundException
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteReviewService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun deleteReview(id: Long) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val review = reviewRepository.findById(id) ?: throw ReviewNotFoundException

        if (user.id != review.userId) throw UserMisMatchException

        reviewRepository.deleteById(review.id)
    }
}
