package org.meogo.domain.review.service

import org.meogo.domain.review.domain.ReviewRepository
import org.meogo.domain.review.enum.KeyWord
import org.meogo.domain.review.exception.KeyWordNotFoundException
import org.meogo.domain.review.exception.ReviewNotFoundException
import org.meogo.domain.review.presentation.dto.request.ModifyReviewRequest
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ModifyReviewService(
    private val reviewRepository: ReviewRepository,
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun modifyReview(reviewId: Long, request: ModifyReviewRequest, image: List<MultipartFile>?) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val review = reviewRepository.findById(reviewId) ?: throw ReviewNotFoundException

        request.keyWord.forEach { keyWord ->
            if (KeyWord.entries.none { it.keyword == keyWord }) throw KeyWordNotFoundException
        }

        review.picture?.let { existingPictures ->
            existingPictures.split(",").forEach { pictureUrl ->
                fileUtil.delete(pictureUrl, Path.REVIEW)
            }
        }
        val imageUrls = image?.joinToString(separator = ",") {
            fileUtil.upload(it, Path.REVIEW)
        }

        val keyWord = request.keyWord.joinToString(separator = ",")

        if (KeyWord.entries.none { it.keyword == keyWord }) throw KeyWordNotFoundException

        if (user.id != review.userId) throw UserMisMatchException

        reviewRepository.save(
            review.updateReview(request.content, request.star, keyWord, imageUrls)
        )
    }
}
