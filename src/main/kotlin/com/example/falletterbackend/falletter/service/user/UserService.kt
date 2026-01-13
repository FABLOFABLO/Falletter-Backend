package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.common.security.TokenProvider
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.dto.user.request.UserSignInRequest
import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.dto.user.response.UserInfoResponse
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.user.IncorrectPasswordException
import com.example.falletterbackend.falletter.facade.auth.AuthFacade
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userFacade: UserFacade,
    private val itemFacade: ItemFacade,
    private val authFacade: AuthFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    @Value("\${cloud.aws.stack.default.image.address}")
    private val defaultImageAddress: String,
    @Value("\${falletter.item.default-brick-count}")
    private val defaultBrickCount: Long,
    @Value("\${falletter.item.default-letter-count}")
    private val defaultLetterCount: Long
) {
    @Transactional
    fun signUp(request: UserSignUpRequest) {
        userFacade.validateSchoolNumberNotExists(request.schoolNumber)
        userFacade.validateEmailNotExists(request.email)

        val imageUrl = if (request.profileImage.isNullOrEmpty()) {
            defaultImageAddress
        } else {
            request.profileImage
        }

        val newUser = User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            schoolNumber = request.schoolNumber,
            name = request.name,
            gender = request.gender,
            theme = request.theme,
            profileImage = imageUrl
        )

        userFacade.saveUser(newUser)

        val userReference = userFacade.getUserReference(newUser.id)

        val item = Item(
            brickCount = defaultBrickCount,
            letterCount = defaultLetterCount,
            user = userReference
        )

        itemFacade.save(item)
    }

    @Transactional
    fun signIn(request: UserSignInRequest): AuthTokenResponse {
        val user = userFacade.getByAccountId(request.email)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IncorrectPasswordException
        }
        return tokenProvider.generateToken(request.email)
    }

    @Transactional
    fun logout() {
        val currentUser = userFacade.getCurrentUser()
        authFacade.deleteRefreshTokenByEmail(currentUser.email)
    }

    @Transactional(readOnly = true)
    fun getInfo(): UserInfoResponse {
        val user = userFacade.getCurrentUser()

        return UserInfoResponse(
            email = user.email,
            schoolNumber = user.schoolNumber,
            name = user.name,
            gender = user.gender,
            theme = user.theme,
            profileImage = user.profileImage,
            id = user.id
        )
    }

    @Transactional(readOnly = true)
    @Cacheable(value = ["students"], key = "'all'")
    fun getAllStudents(): List<UserGetAllStudentResponse> {
        return userFacade.getAllStudents()
    }
}
