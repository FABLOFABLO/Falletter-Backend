package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityDeletePostService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()
        communityFacade.getCommunityWithOwnerCheck(id, user.id)

        communityFacade.deleteCommunity(id)
    }
}
