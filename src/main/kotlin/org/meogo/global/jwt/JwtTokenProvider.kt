package org.meogo.global.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.meogo.global.auth.AuthDetailsService
import org.meogo.global.jwt.dto.TokenResponse
import org.meogo.global.jwt.entity.RefreshToken
import org.meogo.global.jwt.entity.repository.RefreshTokenRepository
import org.meogo.global.jwt.exception.ExpiredTokenException
import org.meogo.global.jwt.exception.InvalidJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    companion object {
        private const val ACCESS_KEY = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }

    fun getToken(name: String): TokenResponse {
        val accessToken = generateAccessToken(name, ACCESS_KEY, jwtProperties.accessExp)
        val refreshToken = generateRefreshToken(REFRESH_KEY, jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(name, refreshToken, jwtProperties.refreshExp)
        )
        return TokenResponse(accessToken, refreshToken)
    }

    private fun generateAccessToken(name: String, type: String, expiration: Long): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setSubject(name)
            .setHeaderParam("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .compact()
    }

    private fun generateRefreshToken(type: String, ttl: Long): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setHeaderParam("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + ttl * 1000))
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization")
        return parseToken(bearer)
    }

    fun parseToken(bearerToken: String?): String? {
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.removePrefix("Bearer ")
        } else {
            null
        }
    }

    fun authorization(token: String): UsernamePasswordAuthenticationToken {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getTokenSubject(token: String): String {
        return getTokenBody(token).subject
    }

    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token).body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException
        } catch (e: InvalidClaimException) {
            throw InvalidJwtException
        } catch (e: Exception) {
            throw InvalidJwtException
        }
    }
}
