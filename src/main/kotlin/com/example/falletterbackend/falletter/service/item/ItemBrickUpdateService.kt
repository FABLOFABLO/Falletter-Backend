package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.BrickItemUpdateRequest
import com.example.falletterbackend.falletter.entity.brick.repository.BrickRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemBrickUpdateService(
    private val brickRepository: BrickRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: BrickItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val brick = brickRepository.findEntityByUser(user)

        brick.changeBrickCount(request.brickUpdate)
    }
}