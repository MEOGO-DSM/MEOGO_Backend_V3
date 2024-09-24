package org.meogo.domain.review.presentation.dto.request

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Size

data class ReviewRequest(
    @field:Size(min = 1, max = 300)
    val content: String,
    val schoolId: Int,
    @field:Range(min = 1, max = 5)
    val star: Int,
    val keyWord: List<String>
)
