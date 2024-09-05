package org.meogo.domain.review.service

import org.meogo.domain.review.exception.UserMisMatchException
import org.meogo.domain.review.presentation.dto.request.ModifyReviewRequest
import org.meogo.domain.review.repository.ReviewRepository
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyReviewService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun modifyReview(reviewId: Long, request: ModifyReviewRequest) {
        val user = userFacade.currentUser()
        val review = reviewRepository.findById(reviewId)

        if (user!!.id != review!!.userId) throw UserMisMatchException

        reviewRepository.save(
            review.updateReview(request.content, request.star, review.picture)
        )
    }
}
