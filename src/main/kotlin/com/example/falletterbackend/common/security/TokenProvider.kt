package com.example.falletterbackend.common.security

import com.example.falletterbackend.common.exception.TokenExpiredException
import com.example.falletterbackend.common.exception.TokenInvalidException
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.entity.auth.RefreshToken
import com.example.falletterbackend.falletter.entity.auth.repository.RefreshTokenRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

@Component
class TokenProvider(
    private val tokenProperties: TokenProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun generateToken(email: String): AuthTokenResponse {
        val accessToken: String = generateAccessToken(email)
        val refreshToken: String = generateRefreshToken(email)
        return AuthTokenResponse(accessToken, refreshToken)
    }

    fun generateAccessToken(email: String): String {
        return createToken(email, "access", tokenProperties.accessExp)
    }

    fun generateRefreshToken(email: String): String {
        val refreshToken = createToken(email, "refresh", tokenProperties.refreshExp)
        refreshTokenRepository.save(RefreshToken(email, refreshToken))
        return refreshToken
    }

    private fun createToken(email: String, typ: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("typ", typ)
            .signWith(SignatureAlgorithm.HS256, tokenProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .compact()
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        println(getAccountId(token))
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getAccountId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getAccountId(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(tokenProperties.secretKey)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenInvalidException
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {

        val bearerToken = request.getHeader(tokenProperties.header)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenProperties.prefix)
            && bearerToken.length > tokenProperties.prefix.length + 1
        ) {
            bearerToken.substring(tokenProperties.prefix.length)
        } else null

    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(tokenProperties.secretKey).parseClaimsJws(token).body
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getSecretKey(): String = tokenProperties.secretKey
}