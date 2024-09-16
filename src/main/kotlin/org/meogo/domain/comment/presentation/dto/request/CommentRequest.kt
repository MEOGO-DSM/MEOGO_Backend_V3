package org.meogo.domain.comment.presentation.dto.request

import javax.validation.constraints.Size

data class CommentRequest(
    @field: Size(min = 1, max = 100)
    val content: String,
    val postId: Long
)
