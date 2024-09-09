package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.presentation.dto.response.ReviewPictureResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryReviewPictureService(
    private val reviewRepository: ReviewRepository
) {

    @Transactional
    fun queryReviewPicture(schoolId: Int): List<ReviewPictureResponse> {
        val reviews = reviewRepository.findAllBySchoolId(schoolId) ?: return emptyList()

        val pictures = reviews
            .mapNotNull { it.picture?.let { pic -> ReviewPictureResponse(pic) } }

        return pictures
    }
}
