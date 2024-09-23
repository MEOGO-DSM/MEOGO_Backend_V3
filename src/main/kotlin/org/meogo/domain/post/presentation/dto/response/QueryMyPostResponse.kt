package org.meogo.domain.post.presentation.dto.response

data class QueryMyPostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: String,
    val good: Int,
    val comment: Int
)
