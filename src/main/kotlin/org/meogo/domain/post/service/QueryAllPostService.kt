package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.presentation.dto.response.PostResponse
import org.meogo.global.s3.FileUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryAllPostService(
    private val postRepository: PostRepository,
    private val fileUtil: FileUtil
) {

    @Transactional(readOnly = true)
    fun execute(): List<PostResponse> {
        val posts = postRepository.findAll()

        return posts.map { post ->
            PostResponse(
                post,
                fileUtil
            )
        }.sortedByDescending { it.id }
    }
}
