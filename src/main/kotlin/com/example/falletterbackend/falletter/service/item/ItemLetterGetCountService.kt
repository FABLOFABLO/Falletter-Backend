package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemLetterGetCountService(
    private val userFacade: UserFacade,
    private val itemRepository: ItemRepository
) {
    @Transactional(readOnly = true)
    fun execute(): ItemLetterGetCountResponse {
        val user = userFacade.getCurrentUser()

        return itemRepository.findLetterCountByUser(user)
    }
}
