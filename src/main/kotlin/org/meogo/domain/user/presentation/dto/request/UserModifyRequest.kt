package org.meogo.domain.user.presentation.dto.request

data class UserModifyRequest(
    val name: String,
    val enrolledSchool: Int
)
