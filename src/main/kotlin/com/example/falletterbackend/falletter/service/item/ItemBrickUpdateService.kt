package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemBrickItemUpdateRequest
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemBrickUpdateService(
    private val itemFacade: ItemFacade,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: ItemBrickItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val item = itemFacade.getUserItem(user)
        item.changeBrickCount(request.brickUpdate)
    }
}
