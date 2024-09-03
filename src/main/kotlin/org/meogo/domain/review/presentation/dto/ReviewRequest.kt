package org.meogo.domain.review.presentation.dto

import javax.validation.constraints.Size

data class ReviewRequest(
    @field:Size(min = 1, max = 300)
    val content: String,
    val schoolId: Int,
    val star: Long,
    val image: String?
)
