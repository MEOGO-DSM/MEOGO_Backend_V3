package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.presentation.dto.response.PostResponse
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
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
            val image = post.image?.let { fileUtil.generateObjectUrl(it, Path.COMMUNITY) }
            PostResponse(
                post,
                image
            )
        }.sortedWith(
            compareByDescending<PostResponse> { it.good }
                .thenByDescending { it.id }
        )
    }
}
