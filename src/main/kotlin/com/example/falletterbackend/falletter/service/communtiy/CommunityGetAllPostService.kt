package com.example.falletterbackend.falletter.service.communtiy

import com.example.falletterbackend.falletter.dto.community.response.CommunityPostsResponse
import com.example.falletterbackend.falletter.dto.community.response.PostListUserResponse
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityGetAllPostService(
    private val communityRepository: CommunityRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<CommunityPostsResponse>{
        val feeds = communityRepository.findAll()

        return feeds.map {
            CommunityPostsResponse(
                id = it.id,
                title = it.title,
                content = it.content,
                author = PostListUserResponse(it.author.name),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }.sortedByDescending { it.id }
    }
}