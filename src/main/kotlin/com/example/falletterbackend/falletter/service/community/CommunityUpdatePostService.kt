package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityUpdatePostService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade
) {
    @Transactional
    fun execute(id: Long, communityPostsRequest: CommunityPostsRequest) {
        val user = userFacade.getCurrentUser()
        val community = communityFacade.getCommunityWithOwnerCheck(id, user.id)

        community.update(communityPostsRequest.title, communityPostsRequest.content)
    }
}
