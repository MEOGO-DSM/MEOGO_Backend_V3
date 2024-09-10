package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.present.dto.response.PostResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuerySchoolPostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun execute(schoolId: Int): List<PostResponse> {
        val posts = postRepository.findBySchoolId(schoolId)

        return posts.map { post ->
            PostResponse(
                id = post.id,
                name = "익명",
                title = post.title,
                content = post.content,
                date = post.format(post.date),
                keyWord = post.keyWord?.split(",")?.map { it.trim() },
                schoolId = post.schoolId
            )
        }
    }
}
