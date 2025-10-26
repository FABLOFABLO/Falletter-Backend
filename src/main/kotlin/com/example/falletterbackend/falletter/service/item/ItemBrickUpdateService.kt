package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemBrickItemUpdateRequest
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemBrickUpdateService(
    private val itemRepository: ItemRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: ItemBrickItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val brick = itemRepository.findEntityByUser(user)

        brick.changeBrickCount(request.brickUpdate)
    }
}