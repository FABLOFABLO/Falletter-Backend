package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityGetPostService(
    private val communityFacade: CommunityFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): CommunityPostsResponse {
        val community = communityFacade.getCurrentCommunity(id)
        return CommunityPostsResponse.from(community)
    }
}
