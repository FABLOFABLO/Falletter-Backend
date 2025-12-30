package com.example.falletterbackend.falletter.service.communtiy

import com.example.falletterbackend.common.exception.AccessDeniedException
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.exception.community.CommunityAlreadyDeletedException
import com.example.falletterbackend.falletter.exception.community.CommunityNotFoundException
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminCommunityDeleteService(
    private val communityRepository: CommunityRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(communityId: Long) {
        val user = userFacade.getCurrentUser()

        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }

        val community = communityRepository.findByIdOrNull(communityId)
            ?: throw CommunityNotFoundException

        if (community.isDeleted) {
            throw CommunityAlreadyDeletedException
        }

        community.delete()
    }
}