package org.meogo.domain.review.presentation.dto.response

data class ReviewListResponse(
    val count: Int = 0,
    val reviews: List<ReviewResponse> = emptyList()
)

data class ReviewResponse(
    val id: Long,
    val content: String,
    val date: String,
    val userName: String,
    val star: Int,
    val image: List<String>?
)
