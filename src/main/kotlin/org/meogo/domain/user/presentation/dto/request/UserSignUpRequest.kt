package org.meogo.domain.user.presentation.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserSignUpRequest(
    @field:NotNull
    @field:Size(min = 4, max = 20, message = "아이디는 4~20자 이내여야 합니다")
    val accountId: String,

    @field:NotNull
    @field:Size(min = 12, max = 20, message = "비밀번호는 12~20자 이내여야 합니다")
    val password: String,

    @field:NotNull
    val name: String,

    val enrolledSchool: String?
)
