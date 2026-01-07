package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignUpService(
    private val userFacade: UserFacade,
    private val itemFacade: ItemFacade,
    private val passwordEncoder: PasswordEncoder,
    @Value("\${cloud.aws.stack.default.image.address}")
    private val defaultImageAddress: String,
    @Value("\${falletter.item.default-brick-count}")
    private val defaultBrickCount: Long,
    @Value("\${falletter.item.default-letter-count}")
    private val defaultLetterCount: Long
) {

    @Transactional
    fun execute(request: UserSignUpRequest) {
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
}
