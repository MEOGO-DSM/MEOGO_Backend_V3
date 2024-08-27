package org.meogo.global.jwt.entity.repository

import org.meogo.global.jwt.entity.RefreshToken
import org.springframework.data.repository.Repository

interface RefreshTokenRepository : Repository<RefreshToken, String> {
    fun save(token: RefreshToken)
    fun findByToken(token: String): RefreshToken?
}
