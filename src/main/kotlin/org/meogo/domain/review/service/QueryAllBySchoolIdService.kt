package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.presentation.dto.response.ReviewListResponse
import org.meogo.domain.review.presentation.dto.response.ReviewResponse
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryAllBySchoolIdService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {

    @Transactional(readOnly = true)
    fun queryAllBySchoolId(schoolId: Int): ReviewListResponse {
        val reviews = reviewRepository.findAllBySchoolId(schoolId) ?: emptyList()

        return ReviewListResponse(
            count = reviews.size,
            reviews = reviews.map { review ->
                val user = userFacade.getUserById(review.userId)
                val profile = fileUtil.generateObjectUrl(user.profile, Path.USER)
                val image = review.picture?.split(",")?.map { pic ->
                    fileUtil.generateObjectUrl(pic.trim(), Path.REVIEW)
                } ?: emptyList()
                ReviewResponse(
                    id = review.id,
                    content = review.content,
                    date = review.format(review.date),
                    userName = user.name,
                    profile = profile,
                    star = review.star.toFloat(),
                    image = image
                )
            }.sortedByDescending { it.id }
        )
    }
}
