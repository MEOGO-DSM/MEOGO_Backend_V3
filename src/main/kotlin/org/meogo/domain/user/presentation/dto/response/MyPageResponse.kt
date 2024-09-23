package org.meogo.domain.user.presentation.dto.response

data class MyPageResponse(
    val name: String,
    val accountId: String,
    val enrolledSchool: Int?,
    val profile: String
)
