package org.meogo.domain.post.service

import org.meogo.domain.comment.domain.CommentRepository
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.presentation.dto.response.QueryMyPostResponse
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyPostService(
    private val userFacade: UserFacade,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(): List<QueryMyPostResponse> {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val posts = postRepository.findByUserId(user.id!!)

        return posts.map {
                post ->
            val comment = commentRepository.findAllByPost(post).count()
            QueryMyPostResponse(
                id = post.id,
                title = post.title,
                content = post.content,
                date = post.format(post.date),
                good = post.good,
                comment = comment
            )
        }.sortedByDescending { it.date }
    }
}
