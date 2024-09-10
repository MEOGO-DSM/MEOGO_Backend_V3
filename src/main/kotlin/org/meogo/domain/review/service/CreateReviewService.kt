package org.meogo.domain.review.service

import org.meogo.domain.review.domain.Review
import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.presentation.dto.request.ReviewRequest
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateReviewService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: ReviewRequest) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        val keyWord = request.keyWord?.joinToString(separator = ",")

        reviewRepository.save(
            Review(
                date = LocalDateTime.now(),
                userId = user.id!!,
                schoolId = request.schoolId,
                star = request.star,
                content = request.content,
                keyWord = keyWord,
                picture = request.image
            )
        )
    }
}
