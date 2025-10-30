package com.example.falletterbackend.falletter.facade.community

import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.exception.community.CommunityNotFoundException
import org.springframework.stereotype.Component

@Component
class CommunityFacade(
    private val communityRepository: CommunityRepository
) {
    fun getCurrentCommunity(id: Long): Community {
        return communityRepository.findById(id).orElseThrow { CommunityNotFoundException }
    }
}
