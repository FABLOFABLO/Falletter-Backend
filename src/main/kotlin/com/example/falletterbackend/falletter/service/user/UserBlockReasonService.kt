package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserBlockReasonResponse
import com.example.falletterbackend.falletter.entity.block.repository.BlockRepository
import com.example.falletterbackend.falletter.exception.user.BlockNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserBlockReasonService(
    private val userFacade: UserFacade,
    private val blockRepository: BlockRepository
) {
    @Transactional(readOnly = true)
    fun execute(suspendId: Long): UserBlockReasonResponse {
        val user = userFacade.getCurrentUser()
        val block = blockRepository.findById(suspendId)
            .orElseThrow { BlockNotFoundException }

        if (block.user.id != user.id) {
            throw BlockNotFoundException
        }

        return UserBlockReasonResponse(
            id = block.id,
            reason = block.blockReason
        )
    }
}
