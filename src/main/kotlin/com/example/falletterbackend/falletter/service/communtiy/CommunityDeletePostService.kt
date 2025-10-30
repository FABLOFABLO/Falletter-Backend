package com.example.falletterbackend.falletter.service.communtiy

import com.example.falletterbackend.falletter.entity.community.repository.CommunityRepository
import com.example.falletterbackend.falletter.exception.user.UserMismatchException
import com.example.falletterbackend.falletter.facade.community.CommunityFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommunityDeletePostService(
    private val userFacade: UserFacade,
    private val communityFacade: CommunityFacade,
    private val communityRepository: CommunityRepository
) {
    @Transactional
    fun execute(id: Long){
        val user = userFacade.getCurrentUser()
        val community = communityFacade.getCurrentCommunity(id)

        if (user.id != community.author.id) throw UserMismatchException

        communityRepository.deleteById(id)
    }
}
