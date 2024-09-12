package org.meogo.domain.review.service

import org.meogo.domain.review.domain.Review
import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.exception.InvalidStarRangeException
import org.meogo.domain.review.presentation.dto.request.ReviewRequest
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class CreateReviewService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun execute(request: ReviewRequest, image: List<MultipartFile>?) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        if (request.star > 5.0 || request.star < 0.0) throw InvalidStarRangeException

        val keyWord = request.keyWord?.joinToString(separator = ",")
        val imageUrls = image?.joinToString(separator = ",") {
            fileUtil.upload(it, Path.REVIEW)
        }

        reviewRepository.save(
            Review(
                date = LocalDateTime.now(),
                userId = user.id!!,
                schoolId = request.schoolId,
                star = request.star,
                content = request.content,
                keyWord = keyWord,
                picture = imageUrls
            )
        )
    }
}
