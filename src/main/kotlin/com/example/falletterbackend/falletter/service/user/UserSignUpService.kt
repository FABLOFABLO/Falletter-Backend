package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.request.UserSignUpRequest
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class UserSignUpService(
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository,
    private val passwordEncoder: PasswordEncoder
) {

    // TODO : 기본 이미지 설정
    @Value("\${cloud.aws.stack.default.image.address}")
    private lateinit var defaultImageAddress: String

    @Transactional
    fun execute(request: UserSignUpRequest) {
        if (userRepository.existsBySchoolNumber(request.schoolNumber)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 ${request.schoolNumber} 학번 입니다.")
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
            profileImage = imageUrl
        )

        userRepository.save(newUser)

        val userReference = userRepository.getReferenceById(newUser.id)

        val item = Item(
            brickCount = 5,
            letterCount = 5,
            user = userReference
        )

        itemRepository.save(item)
    }
}