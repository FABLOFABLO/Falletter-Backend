package com.example.falletterbackend.falletter.service.brick

import com.example.falletterbackend.falletter.dto.item.response.BrickGetCountResponse
import com.example.falletterbackend.falletter.entity.brick.repository.BrickRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrickGetCountService(
    private val userFacade: UserFacade,
    private val brickRepository: BrickRepository
) {
    @Transactional(readOnly = true)
    fun execute(): BrickGetCountResponse {
        val user = userFacade.getCurrentUser()

        return brickRepository.findByUser(user)
    }
}
