package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserListResponse
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserListService(
    private val userRepository: UserRepository,
    private val blockRepository: BlockRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<AdminUserListResponse> {
        return userRepository.findAll().map { user ->
            AdminUserListResponse(
                id = user.id,
                schoolNumber = user.schoolNumber,
                name = user.name,
                profileImage = user.profileImage,
                gender = user.gender,
                warningCount = blockRepository.countByUserAndType(user, BlockType.WARNING)
            )
        }
    }
}
