package org.meogo.domain.user.service

import org.meogo.domain.user.exception.UserNotFoundException
import org.meogo.domain.user.facade.UserFacade
import org.meogo.domain.user.presentation.dto.response.MyPageResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MyPageService(
    private val userFacade: UserFacade

) {
    @Transactional
    fun execute(): MyPageResponse {
        val user = userFacade.currentUser() ?: throw UserNotFoundException

        return MyPageResponse(
            user.name,
            user.accountId,
            user.enrolledSchool,
            user.profile
        )
    }
}
