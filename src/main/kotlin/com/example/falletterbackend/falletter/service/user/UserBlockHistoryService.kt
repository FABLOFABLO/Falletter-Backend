package com.example.falletterbackend.falletter.service.user

import com.example.falletterbackend.falletter.dto.user.response.UserBlockHistoryResponse
import com.example.falletterbackend.falletter.facade.block.BlockFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserBlockHistoryService(
    private val userFacade: UserFacade,
    private val blockFacade: BlockFacade
) {
    @Transactional(readOnly = true)
    fun execute(): List<UserBlockHistoryResponse> {
        val user = userFacade.getCurrentUser()
        val blocks = blockFacade.getBlocksByUser(user)

        return blocks.map { block ->
            UserBlockHistoryResponse(
                id = block.id,
                type = block.type,
                days = block.days,
                startDate = block.startDate,
                endDate = block.endDate
            )
        }
    }
}
