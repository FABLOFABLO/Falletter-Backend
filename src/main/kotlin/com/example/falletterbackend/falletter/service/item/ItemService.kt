package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemBrickItemUpdateRequest
import com.example.falletterbackend.falletter.dto.item.request.ItemLetterItemUpdateRequest
import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val userFacade: UserFacade,
    private val itemFacade: ItemFacade
) {
    @Transactional(readOnly = true)
    fun getBrickCount(): ItemBrickGetCountResponse {
        val user = userFacade.getCurrentUser()
        return itemFacade.findBrickCountByUser(user)
    }

    @Transactional
    fun updateBrickCount(request: ItemBrickItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val item = itemFacade.getUserItem(user)
        item.changeBrickCount(request.brickUpdate)
    }

    @Transactional(readOnly = true)
    fun getLetterCount(): ItemLetterGetCountResponse {
        val user = userFacade.getCurrentUser()
        return itemFacade.findLetterCountByUser(user)
    }

    @Transactional
    fun updateLetterCount(request: ItemLetterItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val item = itemFacade.getUserItem(user)
        item.changeLetterCount(request.letterUpdate)
    }
}
