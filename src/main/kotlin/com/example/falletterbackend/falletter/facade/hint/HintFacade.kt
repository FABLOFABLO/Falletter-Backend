package com.example.falletterbackend.falletter.facade.hint

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.hint.Hint
import com.example.falletterbackend.falletter.entity.hint.repository.HintRepository
import com.example.falletterbackend.falletter.entity.user.User
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

    fun getHintByIdAndUser(id: Long, user: User): HintResponse {
        return hintRepository.findByIdAndUser(id, user)
    }

    fun save(hint: Hint): Hint {
        return hintRepository.save(hint)
    }
}
