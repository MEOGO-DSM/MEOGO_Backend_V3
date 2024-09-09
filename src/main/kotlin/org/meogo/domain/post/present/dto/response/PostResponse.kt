package org.meogo.domain.post.present.dto.response

data class PostResponse(
    val id: Long,
    val name: String,
    val title: String,
    val content: String,
    val date: String,
    val keyWord: List<String>?,
    val schoolId: Int?
)
