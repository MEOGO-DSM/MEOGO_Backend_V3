package org.meogo.domain.comment.presentation.dto.response

import org.meogo.domain.comment.domain.Comment
import org.meogo.domain.post.domain.Post
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path


data class ContentListResponse(
    val count: Int = 0,
    val comments: List<CommentResponse> = emptyList()
)

data class CommentResponse(
    val id: Long,
    val accountId: String,
    val date: String,
    val content: String
){
    constructor(
        comment: Comment,
    ) : this(
        id = comment.id,
        accountId = comment.user.accountId,
        date = comment.format(comment.date),
        content = comment.content
    )
}
