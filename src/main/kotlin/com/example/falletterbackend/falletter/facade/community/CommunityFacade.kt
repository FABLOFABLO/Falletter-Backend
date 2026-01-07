package com.example.falletterbackend.falletter.facade.community

import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.exception.community.CommunityNotFoundException
import com.example.falletterbackend.falletter.exception.user.UserMismatchException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommunityFacade(
    private val communityRepository: CommunityRepository
) {
    fun getCurrentCommunity(id: Long): Community {
        return communityRepository.findByIdOrNull(id) ?: throw CommunityNotFoundException
    }

    fun getCommunityWithOwnerCheck(id: Long, userId: Long): Community {
        val community = getCurrentCommunity(id)
        if (community.author.id != userId) {
            throw UserMismatchException
        }
        return community
    }

    fun getAllCommunities(): List<Community> {
        return communityRepository.findAll()
    }

    fun getAllCommunitiesWithAuthor(): List<Community> {
        return communityRepository.findAllWithAuthorOrderByIdDesc()
    }

    fun saveCommunity(community: Community): Community {
        return communityRepository.save(community)
    }

    fun deleteCommunity(id: Long) {
        communityRepository.deleteById(id)
    }
}
