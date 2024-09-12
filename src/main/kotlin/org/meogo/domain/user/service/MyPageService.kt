package org.meogo.domain.user.service

import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.domain.user.presentation.dto.response.MyPageResponse
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MyPageService(
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {
    @Transactional
    fun execute(): MyPageResponse {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        return MyPageResponse(
            user.name,
            user.accountId,
            user.profile?.let { fileUtil.generateObjectUrl(it, Path.USER) } ?: ""
        )
    }
}
