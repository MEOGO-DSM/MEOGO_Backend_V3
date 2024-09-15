package org.meogo.domain.review.presentation.dto.request

import javax.validation.constraints.Size

data class ModifyReviewRequest(
    @field:Size(min = 1, max = 300)
    val content: String,
    val star: Int,
    val image: List<String>?
)
