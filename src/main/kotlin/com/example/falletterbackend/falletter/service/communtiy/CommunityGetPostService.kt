package com.example.falletterbackend.falletter.service.communtiy

import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.exception.community.CommunityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityGetPostService(
    private val communityRepository: CommunityRepository
) {
    @Transactional
    fun execute(id: Long): CommunityPostsResponse {
        val community = communityRepository.findById(id)
            .orElseThrow { throw CommunityNotFoundException }

        return CommunityPostsResponse.from(community)
    }
}
