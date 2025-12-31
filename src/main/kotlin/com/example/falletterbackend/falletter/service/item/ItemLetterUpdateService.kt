package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemLetterItemUpdateRequest
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.exception.item.ItemNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemLetterUpdateService(
    private val itemRepository: ItemRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: ItemLetterItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val item = itemRepository.findEntityByUser(user)
            ?: throw ItemNotFoundException

        item.changeLetterCount(request.letterUpdate)
    }
}
