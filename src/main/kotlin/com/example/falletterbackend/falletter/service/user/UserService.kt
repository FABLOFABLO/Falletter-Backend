package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.common.security.TokenProvider
import com.example.falletterbackend.falletter.dto.auth.response.AuthTokenResponse
import com.example.falletterbackend.falletter.dto.user.request.UserSignInRequest
import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.dto.user.response.UserInfoResponse
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.notification.Notification
import com.example.falletterbackend.falletter.entity.terms.Terms
import com.example.falletterbackend.falletter.entity.terms.repository.TermsRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.user.IncorrectPasswordException
import com.example.falletterbackend.falletter.facade.auth.AuthFacade
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.notification.NotificationFacade
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
    private val termsRepository: TermsRepository,
    private val notificationFacade: NotificationFacade,
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

        val newUser = createUser(request)
        userFacade.saveUser(newUser)

        val userReference = userFacade.getUserReference(newUser.id)
        createItem(userReference)
        createTerms(userReference, request)
        createNotification(userReference, request.pushNotification)
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

    private fun createUser(request: UserSignUpRequest): User {
        return User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            schoolNumber = request.schoolNumber,
            name = request.name,
            gender = request.gender,
            theme = request.theme,
            profileImage = request.profileImage?.takeIf { it.isNotEmpty() } ?: defaultImageAddress
        )
    }

    private fun createItem(user: User) {
        val item = Item(
            brickCount = defaultBrickCount,
            letterCount = defaultLetterCount,
            user = user
        )
        itemFacade.save(item)
    }

    private fun createTerms(user: User, request: UserSignUpRequest) {
        val terms = Terms(
            user = user,
            serviceTerms = request.serviceTerms,
            privacyPolicy = request.privacyPolicy,
            communityTerms = request.communityTerms,
            pushNotification = request.pushNotification
        )
        termsRepository.save(terms)
    }

    private fun createNotification(user: User, pushEnabled: Boolean) {
        val notification = Notification(
            user = user,
            pushEnabled = pushEnabled,
            commentEnabled = pushEnabled,
            brickActivationEnabled = pushEnabled,
            brickEnabled = pushEnabled,
            letterEnabled = pushEnabled,
            letterSentEnabled = pushEnabled,
            adminNoticeEnabled = pushEnabled
        )
        notificationFacade.save(notification)
    }
}
