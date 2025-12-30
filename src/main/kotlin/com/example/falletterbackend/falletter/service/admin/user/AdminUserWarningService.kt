package com.example.falletterbackend.falletter.service.admin.user

import com.example.falletterbackend.falletter.entity.block.Block
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.entity.user.repository.UserRepository
import com.example.falletterbackend.falletter.exception.user.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AdminUserWarningService(
    private val userRepository: UserRepository,
    private val blockRepository: BlockRepository
) {
    @Transactional
    fun execute(userId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException }

        val warning = Block(
            user = user,
            type = BlockType.WARNING,
            days = 0,
            blockReason = null,
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now()
        )

        blockRepository.save(warning)
    }
}
