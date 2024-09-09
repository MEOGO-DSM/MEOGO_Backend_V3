package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.present.dto.response.PostResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class QueryAllPostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun execute(): List<PostResponse> {
        val posts = postRepository.findAll()

        return posts.map { post ->
            PostResponse(
                id = post.id,
                name = "익명",
                title = post.title,
                content = post.content,
                date = format(post.date),
                keyWord = post.keyWord?.split(",")?.map { it.trim() },
                schoolId = post.schoolId
            )
        }.sortedBy { it.id }
    }

    private fun format(date: LocalDateTime) =
        date.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"))
}
