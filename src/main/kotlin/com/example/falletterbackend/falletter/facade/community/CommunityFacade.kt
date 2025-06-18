package com.example.falletterbackend.falletter.facade.community

import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class CommunityFacade(
    private val communityRepository: CommunityRepository
) {
    fun getCurrentFeed(id: Long): Optional<Community> {
        return communityRepository.findById(id)
    }
}