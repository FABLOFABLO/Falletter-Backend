package com.example.falletterbackend.falletter.service.brick

import com.example.falletterbackend.falletter.dto.brick.request.BrickItemUpdateRequest
import com.example.falletterbackend.falletter.entity.brick.repository.BrickRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrickItemUpdateService(
    private val brickRepository: BrickRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: BrickItemUpdateRequest) {
        val user = userFacade.getCurrentUser()
        val brick = brickRepository.findEntityByUser(user)
            ?: throw IllegalArgumentException("해당 유저의 brick가 존재하지 않습니다.")

        brick.increaseBrickCount(request.brickUpdate)
    }
}