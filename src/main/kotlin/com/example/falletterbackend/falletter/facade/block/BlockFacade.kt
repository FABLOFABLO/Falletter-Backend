package com.example.falletterbackend.falletter.facade.block

import com.example.falletterbackend.falletter.entity.block.Block
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BlockFacade(
    private val blockRepository: BlockRepository
) {
    fun getBlocksByUser(user: User): List<Block> {
        return blockRepository.findAllByUser(user)
    }

    fun getWarningCountByUser(user: User): Long {
        return blockRepository.countByUserAndType(user, BlockType.WARNING)
    }

    fun getActiveBlock(user: User): Block? {
        return blockRepository.findByUserAndEndDateAfter(user, LocalDateTime.now())
    }

    fun createWarning(user: User): Block {
        val now = LocalDateTime.now()
        val warning = Block(
            user = user,
            type = BlockType.WARNING,
            days = 0,
            blockReason = null,
            startDate = now,
            endDate = now
        )
        return blockRepository.save(warning)
    }

    fun createBlock(user: User, days: Int, reason: String): Block {
        val now = LocalDateTime.now()
        val block = Block(
            user = user,
            type = BlockType.BLOCK,
            days = days,
            blockReason = reason,
            startDate = now,
            endDate = now.plusDays(days.toLong())
        )
        return blockRepository.save(block)
    }
}
