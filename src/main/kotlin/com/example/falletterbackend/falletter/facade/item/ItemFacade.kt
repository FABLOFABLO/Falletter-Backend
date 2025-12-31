package com.example.falletterbackend.falletter.facade.item

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
}
