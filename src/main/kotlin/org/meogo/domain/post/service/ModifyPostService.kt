package org.meogo.domain.post.service

import org.meogo.domain.post.domain.PostRepository
import org.meogo.domain.post.exception.PostNotFoundException
import org.meogo.domain.post.presentation.dto.request.PostRequest
import org.meogo.domain.user.exception.UserMisMatchException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ModifyPostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {
    @Transactional
    fun execute(postId: Long, request: PostRequest, image: MultipartFile?) {
        val user = userFacade.currentUser()
        val post = postRepository.findById(postId) ?: throw PostNotFoundException

        if (user!!.id != post.userId) throw UserMisMatchException

        val updateImage = handleImage(post.image, image)
        val schoolId = if (!request.isOk || user.enrolledSchool == null) null else user.enrolledSchool
        val keyWord = request.keyWord?.joinToString(separator = ",")

        postRepository.save(post.update(request.title, request.content, schoolId, keyWord, updateImage))
    }

    private fun handleImage(image: String?, newImage: MultipartFile?): String? {
        image?.let { fileUtil.delete(it, Path.COMMUNITY) }
        return if (newImage == null) {
            null
        } else {
            fileUtil.upload(newImage, Path.COMMUNITY)
        }
    }
}
