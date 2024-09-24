package org.meogo.domain.review.presentation.dto.response

data class ReviewResultResponse(
    val star: Float,
    val tag1: Tag,
    val tag2: Tag,
    val tag3: Tag
)

data class Tag(
    val tagName: String,
    val percentage: Int
)
