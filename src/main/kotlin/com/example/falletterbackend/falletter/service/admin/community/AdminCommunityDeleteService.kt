package com.example.falletterbackend.falletter.service.admin.community

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.exception.community.CommunityAlreadyDeletedException
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminCommunityDeleteService(
    private val communityFacade: CommunityFacade
) {
    @AdminOnly
    @Transactional
    fun execute(communityId: Long) {
        val community = communityFacade.getCurrentCommunity(communityId)

        if (community.isDeleted) {
            throw CommunityAlreadyDeletedException
        }

        community.delete()
    }
}