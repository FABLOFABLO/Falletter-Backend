package com.example.falletterbackend.falletter.service.item

import com.example.falletterbackend.falletter.dto.item.request.LetterItemUpdateRequest
import com.example.falletterbackend.falletter.entity.letter.repository.LetterRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ItemLetterUpdateService(
    private val letterRepository: LetterRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(request: LetterItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val letter = letterRepository.findEntityByUser(user)
            ?: throw IllegalArgumentException("해당 유저의 Letter가 존재하지 않습니다.")

        letter.changeLetterCount(request.letterUpdate)
    }
}