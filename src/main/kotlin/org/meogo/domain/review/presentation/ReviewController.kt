package org.meogo.domain.review.presentation

import lombok.RequiredArgsConstructor
import org.meogo.domain.review.presentation.dto.request.ModifyReviewRequest
import org.meogo.domain.review.presentation.dto.request.ReviewRequest
import org.meogo.domain.review.service.CreateReviewService
import org.meogo.domain.review.service.DeleteReviewService
import org.meogo.domain.review.service.ModifyReviewService
import org.meogo.domain.review.service.QueryAllBySchoolIdService
import org.meogo.domain.review.service.QueryReviewPictureService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
class ReviewController(
    private val createReviewService: CreateReviewService,
    private val queryAllBySchoolIdService: QueryAllBySchoolIdService,
    private val modifyReviewService: ModifyReviewService,
    private val deleteReviewService: DeleteReviewService,
    private val queryReviewPictureService: QueryReviewPictureService
) {
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody
        request: ReviewRequest
    ) =
        createReviewService.execute(request)

    @GetMapping("/query")
    fun queryAllBySchoolId(@RequestParam(name = "school_id") schoolId: Int) =
        queryAllBySchoolIdService.queryAllBySchoolId(schoolId)

    @PatchMapping("/modify")
    fun modify(@RequestParam(name = "review_id") reviewId: Long, @RequestBody request: ModifyReviewRequest) =
        modifyReviewService.modifyReview(reviewId, request)

    @DeleteMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun delete(@RequestParam(name = "review_id") reviewId: Long) =
        deleteReviewService.deleteReview(reviewId)

    @GetMapping("/pic")
    fun queryReviewPicture(@RequestParam(name = "school_id") schoolId: Int) =
        queryReviewPictureService.queryReviewPicture(schoolId)
}
