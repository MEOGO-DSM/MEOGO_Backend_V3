package org.meogo.domain.user.service

import org.meogo.domain.user.domain.UserRepository
import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.domain.user.presentation.dto.request.UserModifyRequest
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ModifyUserInfoService(
    private val userRepository: UserRepository,
    private val userFacade: UserFacade,
    @Value("\${cloud.aws.s3.default-image}")
    private val defaultImage: String,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun execute(request: UserModifyRequest, file: MultipartFile?) {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        if (user.profile != defaultImage) fileUtil.delete(user.profile, Path.USER)
        val url = file?.let { fileUtil.upload(it, Path.USER) } ?: defaultImage

        userRepository.save(
            user.updateProfile(request.name, request.enrolledSchool, url)
        )
    }
}
