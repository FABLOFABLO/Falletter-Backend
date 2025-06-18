package com.example.falletterbackend.falletter.service.communtiy

import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityGetPostService(
    private val communityRepository: CommunityRepository
) {
    @Transactional
    fun execute(id: Long): CommunityPostsResponse {
        val community =  communityRepository.findById(id)
            .orElseThrow { (EntityNotFoundException("게시물 아이디를 찾을 수 없음 : $id")) }

        return CommunityPostsResponse.from(community)
    }
}