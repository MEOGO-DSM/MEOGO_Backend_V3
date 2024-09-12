package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.presentation.dto.response.ReviewPictureResponse
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryReviewPictureService(
    private val reviewRepository: ReviewRepository,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun queryReviewPicture(schoolId: Int): List<ReviewPictureResponse> {
        val reviews = reviewRepository.findAllBySchoolId(schoolId)?.sortedByDescending { it.id } ?: return emptyList()

        val pictures = reviews
            .flatMap { review ->
                review.picture?.split(",")?.map { pic ->
                    val pictureUrl = fileUtil.generateObjectUrl(pic.trim(), Path.REVIEW)
                    ReviewPictureResponse(pictureUrl)
                } ?: emptyList()
            }

        return pictures
    }
}
