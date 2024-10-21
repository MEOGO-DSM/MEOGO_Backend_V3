package org.meogo.domain.review.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.meogo.domain.review.presentation.dto.request.ModifyReviewRequest
import org.meogo.domain.review.presentation.dto.request.ReviewRequest
import org.meogo.domain.review.service.CreateReviewService
import org.meogo.domain.review.service.DeleteReviewService
import org.meogo.domain.review.service.ModifyReviewService
import org.meogo.domain.review.service.QueryAllBySchoolIdService
import org.meogo.domain.review.service.QueryKeywordsService
import org.meogo.domain.review.service.QueryMatchService
import org.meogo.domain.review.service.QueryReviewPictureService
import org.meogo.domain.review.service.QuerySchoolReviewResultService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@Tag(name = "Review API")
@RestController
@RequestMapping("/review")
class ReviewController(
    private val createReviewService: CreateReviewService,
    private val queryAllBySchoolIdService: QueryAllBySchoolIdService,
    private val modifyReviewService: ModifyReviewService,
    private val deleteReviewService: DeleteReviewService,
    private val queryReviewPictureService: QueryReviewPictureService,
    private val querySchoolReviewResultService: QuerySchoolReviewResultService,
    private val queryKeywordsService: QueryKeywordsService,
    private val queryMatchService: QueryMatchService
) {
    @Operation(summary = "리뷰 작성")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(
        @Valid
        @RequestPart("request")
        request: ReviewRequest,
        @RequestPart("image") images: List<MultipartFile>?
    ) =
        createReviewService.execute(request, images)

    @Operation(summary = "학교별 리뷰 조회")
    @GetMapping("/query")
    fun queryAllBySchoolId(@RequestParam(name = "school_id") schoolId: Int) =
        queryAllBySchoolIdService.queryAllBySchoolId(schoolId)

    @Operation(summary = "리뷰 수정")
    @PatchMapping("/modify")
    fun modify(
        @RequestParam(name = "review_id") reviewId: Long,
        @RequestBody request: ModifyReviewRequest,
        @RequestPart("image") images: List<MultipartFile>?
    ) =
        modifyReviewService.modifyReview(reviewId, request, images)

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun delete(@RequestParam(name = "review_id") reviewId: Long) =
        deleteReviewService.deleteReview(reviewId)

    @Operation(summary = "학교별 리뷰 사진 조회")
    @GetMapping("/pic")
    fun queryReviewPicture(@RequestParam(name = "school_id") schoolId: Int) =
        queryReviewPictureService.queryReviewPicture(schoolId)

    @Operation(summary = "라뷰 결과 조회")
    @GetMapping("/query/result")
    fun querySchoolReviewResult(@RequestParam(name = "school_id") schoolId: Int) = querySchoolReviewResultService.execute(schoolId)

    @Operation(summary = "리뷰 키워드 반환")
    @GetMapping("/keyword")
    fun queryKeyword() = queryKeywordsService.execute()

    @Operation(summary = "해당 학교인지 여부 반환")
    @GetMapping("/match")
    fun schoolMatch(@RequestParam(name = "school_id") schoolId: Int) =
        queryMatchService.execute(schoolId)
}
