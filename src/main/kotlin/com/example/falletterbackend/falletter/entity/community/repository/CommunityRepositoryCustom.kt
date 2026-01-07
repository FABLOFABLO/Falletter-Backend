package com.example.falletterbackend.falletter.entity.community.repository

import com.example.falletterbackend.falletter.entity.community.Community

interface CommunityRepositoryCustom {
    fun findAllWithAuthorOrderByIdDesc(): List<Community>
}
