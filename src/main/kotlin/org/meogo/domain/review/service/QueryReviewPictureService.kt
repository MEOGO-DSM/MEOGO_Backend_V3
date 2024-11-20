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
        val picturesByYear = reviews
            .flatMap { review ->
                val year = review.date.year
                review.picture?.split(",")?.map { pic ->
                    val imgUrl = fileUtil.generateObjectUrl(pic.trim(), Path.REVIEW)
                    Pair(year, imgUrl)
                } ?: emptyList()
            }
            .groupBy { it.first }

        return picturesByYear.map { (year, urls) ->
            ReviewPictureResponse(year, urls.map { it.second })
        }
    }
}
