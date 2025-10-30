package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.ItemLetterItemUpdateRequest
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
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
            ?: throw IllegalArgumentException("해당 유저의 Letter가 존재하지 않습니다.")

        item.changeLetterCount(request.letterUpdate)
    }
}
