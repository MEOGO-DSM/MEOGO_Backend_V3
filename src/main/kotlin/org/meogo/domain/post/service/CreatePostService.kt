package org.meogo.domain.post.service

import org.meogo.domain.post.domain.Post
import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.present.dto.request.PostRequest
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class CreatePostService(
    private val userFacade: UserFacade,
    private val postRepository: PostRepository,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun execute(request: PostRequest, image: MultipartFile?) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        val schoolId = if (!request.isOk || user.enrolledSchool == null) null else user.enrolledSchool

        val keyWord = request.keyWord?.joinToString(separator = ",")

        postRepository.save(
            Post(
                title = request.title,
                content = request.content,
                userId = user.id!!,
                date = LocalDateTime.now(),
                schoolId = schoolId,
                keyWord = keyWord,
                image = image?.let { fileUtil.upload(it, Path.COMMUNITY) }
            )
        )
    }
}
