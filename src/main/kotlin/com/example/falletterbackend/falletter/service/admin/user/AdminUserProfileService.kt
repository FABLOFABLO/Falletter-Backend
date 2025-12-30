package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.response.AdminUserProfileResponse
import com.example.falletterbackend.falletter.dto.admin.user.response.BlockResponse
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserProfileService(
    private val userRepository: UserRepository,
    private val blockRepository: BlockRepository
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): AdminUserProfileResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException }

        val blocks = blockRepository.findAllByUser(user).map { block ->
            BlockResponse(
                id = block.id,
                type = block.type,
                days = block.days,
                reason = block.blockReason,
                startDate = block.startDate,
                endDate = block.endDate,
                createdAt = block.createdAt
            )
        }

        val warningCount = blockRepository.countByUserAndType(user, BlockType.WARNING)

        return AdminUserProfileResponse(
            id = user.id,
            schoolNumber = user.schoolNumber,
            name = user.name,
            profileImage = user.profileImage,
            gender = user.gender,
            warningCount = warningCount,
            blocks = blocks
        )
    }
}
