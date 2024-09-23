package org.meogo.domain.user.service

import org.meogo.domain.user.domain.UserRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UploadProfileService(
    private val fileUtil: FileUtil,
    private val userFacade: UserFacade,
    private val userRepository: UserRepository,
    @Value("\${cloud.aws.s3.default-image}")
    private val defaultImage: String

) {

    @Transactional
    fun uploadProfile(file: MultipartFile) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        if (user.profile != defaultImage) fileUtil.delete(user.profile, Path.USER)
        val url = fileUtil.upload(file, Path.USER)
        user.updateProfile(url)
        userRepository.save(user)
    }
}
