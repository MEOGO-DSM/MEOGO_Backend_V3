package org.meogo.domain.post.service

import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.comment.presentation.dto.response.CommentResponse
import org.meogo.domain.comment.presentation.dto.response.ContentListResponse
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.exception.PostNotFoundException
import org.meogo.domain.post.presentation.dto.response.PostDetailResponse
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryPostDetailService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional(readOnly = true)
    fun execute(postId: Long): PostDetailResponse {
        val post = postRepository.findById(postId) ?: throw PostNotFoundException

        val comments = commentRepository.findByPost(post)
        val contentListResponse = ContentListResponse(
            count = comments.size,
            comments = comments.map { CommentResponse(it) }.sortedByDescending { it.date }
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
