package com.example.falletterbackend.falletter.facade.user

import com.example.falletterbackend.falletter.dto.user.response.UserGetAllStudentResponse
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.AlreadyAccountIdException
import com.example.falletterbackend.falletter.exception.user.AlreadyEmailException
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return getByAccountId(email)
    }

    fun checkAccountIdExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException
    }

    fun getByAccountId(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException
    }

    fun getAllUsers(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    fun getAllStudents(): List<UserGetAllStudentResponse> {
        return userRepository.findAllBy()
    }

    fun validateSchoolNumberNotExists(schoolNumber: String) {
        if (userRepository.existsBySchoolNumber(schoolNumber)) {
            throw AlreadyAccountIdException
        }
    }

    fun validateEmailNotExists(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw AlreadyEmailException
        }
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getUserReference(userId: Long): User {
        return userRepository.getReferenceById(userId)
    }
}
