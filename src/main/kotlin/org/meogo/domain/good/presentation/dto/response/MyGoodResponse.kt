package org.meogo.domain.good.presentation.dto.response

data class MyGoodResponse(
    val postId: Long,
    val title: String,
    val name: String,
    val good: Int,
    val date: String,
    val schoolId: Int?
)
