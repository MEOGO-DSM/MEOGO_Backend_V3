package org.meogo.domain.comment.presentation.dto.response

import org.meogo.domain.comment.domain.Comment

data class CommentListResponse(
    val count: Int = 0,
    val commentList: List<CommentResponse> = emptyList()
)

data class CommentResponse(
    val id: Long,
    val accountId: String,
    val date: String,
    val content: String,
    val replies: List<CommentResponse> = emptyList()
) {
    constructor(comment: Comment) : this(
        id = comment.id,
        accountId = comment.user.accountId,
        date = comment.format(comment.date),
        content = comment.content,
        replies = comment.replies.map { CommentResponse(it) }
    )

    constructor(comment: Comment, replies: List<CommentResponse>) : this(
        id = comment.id,
        accountId = comment.user.accountId,
        date = comment.format(comment.date),
        content = comment.content,
        replies = replies
    )
}
