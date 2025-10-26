package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class
ItemBrickGetCountService(
    private val userFacade: UserFacade,
    private val itemRepository: ItemRepository
) {
    @Transactional(readOnly = true)
    fun execute(): ItemBrickGetCountResponse {
        val user = userFacade.getCurrentUser()

        return itemRepository.findBrickCountByUser(user)
    }
}
