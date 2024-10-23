package org.meogo.domain.post.presentation.dto.response

import org.meogo.domain.post.domain.Post

data class PostResponse(
    val id: Long,
    val name: String,
    val title: String,
    val content: String,
    val date: String,
    val keyWord: List<String>?,
    val schoolId: Int?,
    val image: String?,
    val good: Int
) {
    constructor(
        post: Post,
        image: String?
    ) : this(
        post.id,
        "익명",
        post.title,
        post.content,
        post.format(post.date),
        post.keyWord?.split(",")?.map { it.trim() } ?: emptyList(),
        post.schoolId,
        image?.substringBefore("?"),
        post.good
    )
}
