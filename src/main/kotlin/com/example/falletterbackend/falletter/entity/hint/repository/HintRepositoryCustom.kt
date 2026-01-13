package com.example.falletterbackend.falletter.entity.hint.repository

import com.example.falletterbackend.falletter.dto.hint.response.HintResponse
import com.example.falletterbackend.falletter.entity.user.User

interface HintRepositoryCustom {
    fun findByIdAndUser(id: Long, user: User): HintResponse?
}
