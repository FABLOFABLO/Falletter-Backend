package com.example.falletterbackend.falletter.facade.hint

import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.entity.hint.repository.HintRepository
import com.example.falletterbackend.falletter.exception.hint.HintNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class HintFacade(
    private val hintRepository: HintRepository
) {
    fun getCurrentHint(id: Long): Hint {
        return hintRepository.findByIdOrNull(id) ?: throw HintNotFoundException
    }
}