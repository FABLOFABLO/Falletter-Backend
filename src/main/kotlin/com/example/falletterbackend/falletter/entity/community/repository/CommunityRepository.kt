package com.example.falletterbackend.falletter.entity.community.repository

import com.example.falletterbackend.falletter.entity.community.Community
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CommunityRepository : JpaRepository<Community, Long>, CommunityRepositoryCustom {
    override fun findById(id: Long): Optional<Community>
}
