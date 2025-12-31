package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemLetterItemUpdateRequest
import com.example.falletterbackend.falletter.facade.item.ItemFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemLetterUpdateService(
    private val itemFacade: ItemFacade,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: ItemLetterItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val item = itemFacade.getUserItem(user)
        item.changeLetterCount(request.letterUpdate)
    }
}
