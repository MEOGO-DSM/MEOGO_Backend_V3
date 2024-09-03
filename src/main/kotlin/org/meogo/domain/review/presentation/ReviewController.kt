package org.meogo.domain.review.presentation

import lombok.RequiredArgsConstructor
import org.meogo.domain.review.presentation.dto.ReviewRequest
import org.meogo.domain.review.service.CreateReviewService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
class ReviewController(
    private val createReviewService: CreateReviewService
) {
    @PostMapping
    fun create(
        @Valid @RequestBody
        request: ReviewRequest
    ) =
        createReviewService.execute(request)
}
