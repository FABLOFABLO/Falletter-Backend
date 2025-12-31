package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.AlreadyAccountIdException
import com.example.falletterbackend.falletter.exception.user.AlreadyEmailException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignUpService(
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository,
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
        if (userRepository.existsBySchoolNumber(request.schoolNumber)) {
            throw AlreadyAccountIdException
        }

        if (userRepository.existsByEmail(request.email)) {
            throw AlreadyEmailException
        }

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

        userRepository.save(newUser)

        val userReference = userRepository.getReferenceById(newUser.id)

        val item = Item(
            brickCount = defaultBrickCount,
            letterCount = defaultLetterCount,
            user = userReference
        )

        itemRepository.save(item)
    }
}
