package org.meogo.domain.review.service

import org.meogo.domain.review.presentation.dto.response.ReviewResponse
import org.meogo.domain.review.repository.ReviewRepository
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class QueryAllBySchoolIdService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade
) {

    @Transactional(readOnly = true)
    fun queryAllBySchoolId(schoolId: Int): List<ReviewResponse> {
        val reviews = reviewRepository.findAllBySchoolId(schoolId)

        return reviews.map { review ->
            ReviewResponse(
                id = review.id,
                userName = userFacade.getUserById(review.userId).name,
                content = review.content,
                date = format(review.date),
                star = review.star,
                picture = review.picture ?: ""
            )
        }.sortedBy { it.id }
    }

    private fun format(date: LocalDateTime) =
        date.format(DateTimeFormatter.ofPattern("MM.dd HH:mm")).toString()
}
