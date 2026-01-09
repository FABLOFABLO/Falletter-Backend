package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.dto.community.request.CommunityPostsRequest
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsListResponse
import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade
) {
    @Transactional
    fun createPost(request: CommunityPostsRequest) {
        val user = userFacade.getCurrentUser()

        communityFacade.saveCommunity(
            Community(
                title = request.title,
                content = request.content,
                author = user
            )
        )
    }

    @Transactional(readOnly = true)
    fun getAllPosts(): List<CommunityPostsListResponse> {
        val communities = communityFacade.getAllCommunitiesWithAuthor()

        return communities.map { CommunityPostsListResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getPost(id: Long): CommunityPostsResponse {
        val community = communityFacade.getCurrentCommunity(id)
        return CommunityPostsResponse.from(community)
    }

    @Transactional
    fun updatePost(id: Long, request: CommunityPostsRequest) {
        val user = userFacade.getCurrentUser()
        val community = communityFacade.getCommunityWithOwnerCheck(id, user.id)

        community.update(request.title, request.content)
    }

    @Transactional
    fun deletePost(id: Long) {
        val user = userFacade.getCurrentUser()
        communityFacade.getCommunityWithOwnerCheck(id, user.id)

        communityFacade.deleteCommunity(id)
    }
}
