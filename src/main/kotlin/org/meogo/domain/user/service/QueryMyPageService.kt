package org.meogo.domain.user.service

import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.domain.user.presentation.dto.response.MyPageResponse
import org.meogo.global.s3.FileUtil
import org.meogo.global.s3.Path
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyPageService(
    private val userFacade: UserFacade,
    private val fileUtil: FileUtil
) {
    @Transactional
    fun execute(): MyPageResponse {
        val user = userFacade.currentUser() ?: throw UserNotFoundException
        val image = fileUtil.generateObjectUrl(user.profile, Path.USER)

        return MyPageResponse(
            user.name,
            user.accountId,
            user.enrolledSchool,
            image.substringBefore("?")
        )
    }
}
