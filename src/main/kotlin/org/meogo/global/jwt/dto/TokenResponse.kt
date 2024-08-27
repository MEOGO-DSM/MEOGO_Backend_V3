package org.meogo.global.jwt.dto
data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
