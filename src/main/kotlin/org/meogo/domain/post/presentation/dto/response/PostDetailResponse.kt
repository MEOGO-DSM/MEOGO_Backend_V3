package org.meogo.domain.post.presentation.dto.response

import org.meogo.domain.comment.presentation.dto.response.ContentListResponse
import org.meogo.domain.post.domain.Post
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path

data class PostDetailResponse(
    val id: Long,
    val name: String,
    val title: String,
    val content: String,
    val date: String,
    val keyWord: List<String>?,
    val schoolId: Int?,
    val image: String?,
    val good: Int,
    val comments: ContentListResponse
)