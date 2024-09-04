package org.meogo.domain.review.presentation.dto.response

data class ReviewResponse(
    val id: Long,
    val content: String,
    val date: String,
    val userName: String,
    val star: Long,
    val picture: String?
)
