package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.dto.admin.user.request.AdminUserBlockRequest
import com.example.falletterbackend.falletter.entity.block.Block
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AdminUserBlockService(
    private val userRepository: UserRepository,
    private val blockRepository: BlockRepository
) {
    @Transactional
    fun execute(userId: Long, request: AdminUserBlockRequest) {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException }

        val now = LocalDateTime.now()
        val block = Block(
            user = user,
            type = BlockType.BLOCK,
            days = request.days,
            blockReason = request.reason,
            startDate = now,
            endDate = now.plusDays(request.days.toLong())
        )

        blockRepository.save(block)
    }
}
