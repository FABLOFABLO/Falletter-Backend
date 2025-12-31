package com.example.falletterbackend.falletter.service.community

import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.exception.community.CommunityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityGetPostService(
    private val communityRepository: CommunityRepository
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): CommunityPostsResponse {
        val community = communityRepository.findByIdOrNull(id)
            ?: throw CommunityNotFoundException

        return CommunityPostsResponse.from(community)
    }
}
