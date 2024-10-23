package org.meogo.domain.user.presentation.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserSignUpRequest(
    @field:NotNull
    @field:Size(min = 5, max = 15, message = "아이디는 5~15자 이내여야 합니다")
    @field:Pattern(
        regexp = "^[a-z0-9]+$",
        message = "아이디는 소문자 알파벳과 숫자만 포함할 수 있습니다."
    )
    val accountId: String,

    @field:NotNull
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_.]).{8,20}$",
        message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 최소 1자 포함해야 합니다."
    )
    val password: String,

    @field:NotNull
    val name: String,

    val enrolledSchool: String?
)
