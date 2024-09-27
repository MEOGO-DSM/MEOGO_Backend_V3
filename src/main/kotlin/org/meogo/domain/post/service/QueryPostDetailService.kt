package org.meogo.domain.post.service

import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.presentation.dto.response.CommentListResponse
import org.meogo.domain.comment.service.CommentService
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.presentation.dto.response.PostDetailResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryPostDetailService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val commentService: CommentService
) {
    @Transactional(readOnly = true)
    fun execute(postId: Long): PostDetailResponse {
        val post = postRepository.findById(postId)

        val basicComments = commentRepository.findAllByPost(post)

        val replies = commentService.getCommentResponses(basicComments)

        val contentListResponse = CommentListResponse(
            count = replies.size + basicComments.size,
            commentList = replies.sortedByDescending { it.date }
        )

        return PostDetailResponse(
            id = post.id,
            name = "익명",
            title = post.title,
            content = post.content,
            date = post.format(post.date),
            keyWord = post.keyWord?.split(","),
            schoolId = post.schoolId,
            image = post.image,
            good = post.good,
            comments = contentListResponse
        )
    }
}
