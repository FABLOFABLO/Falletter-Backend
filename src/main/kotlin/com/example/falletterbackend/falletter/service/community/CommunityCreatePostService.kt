package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityCreatePostService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade
) {
    @Transactional
    fun execute(request: CommunityPostsRequest) {
        val user = userFacade.getCurrentUser()

        communityFacade.saveCommunity(
            Community(
                title = request.title,
                content = request.content,
                author = user
            )
        )
    }
}
