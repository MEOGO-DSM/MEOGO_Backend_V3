package org.meogo.domain.post.presentation.dto.response

import org.meogo.domain.post.domain.Post
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path

data class PostResponse(
    val id: Long,
    val name: String,
    val title: String,
    val content: String,
    val date: String,
    val keyWord: List<String>?,
    val schoolId: Int?,
    val image: String?
) {
    constructor(
        post: Post,
        fileUtil: FileUtil
    ) : this(
        post.id,
        "익명",
        post.title,
        post.content,
        post.format(post.date),
        post.keyWord?.split(",")?.map { it.trim() } ?: emptyList(),
        post.schoolId,
        post.image?.let { fileUtil.generateObjectUrl(it, Path.COMMUNITY) }
    )
}
