package com.example.falletterbackend.falletter.service.admin.community

import com.example.falletterbackend.falletter.exception.auth.AccessDeniedException
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.exception.community.CommunityAlreadyDeletedException
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminCommunityDeleteService(
    private val communityFacade: CommunityFacade,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(communityId: Long) {
        val user = userFacade.getCurrentUser()

        if (user.role != Role.ADMIN) {
            throw AccessDeniedException
        }

        val community = communityFacade.getCurrentCommunity(communityId)

        if (community.isDeleted) {
            throw CommunityAlreadyDeletedException
        }

        community.delete()
    }
}