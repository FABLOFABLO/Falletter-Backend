package com.example.falletterbackend.falletter.facade.item

import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.item.repository.ItemRepository
import com.example.falletterbackend.falletter.entity.user.User
import com.example.falletterbackend.falletter.exception.item.ItemNotFoundException
import com.example.falletterbackend.falletter.exception.letter.LetterCountInsufficientException
import org.springframework.stereotype.Component

@Component
class ItemFacade(
    private val itemRepository: ItemRepository
) {
    fun getUserItem(user: User): Item {
        return itemRepository.findEntityByUser(user)
            ?: throw ItemNotFoundException
    }

    fun validateLetterAvailable(user: User) {
        if (!itemRepository.existsByUserAndLetterCountGreaterThan(user, 0)) {
            throw LetterCountInsufficientException
        }
    }

    fun save(item: Item): Item {
        return itemRepository.save(item)
    }

    fun findBrickCountByUser(user: User): ItemBrickGetCountResponse {
        return itemRepository.findBrickCountByUser(user)
            ?: throw ItemNotFoundException
    }

    fun findLetterCountByUser(user: User): ItemLetterGetCountResponse {
        return itemRepository.findLetterCountByUser(user)
            ?: throw ItemNotFoundException
    }
}
