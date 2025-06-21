package com.example.falletterbackend.falletter.entity.brick.repository

import com.example.falletterbackend.falletter.dto.brick.response.BrickGetCountResponse
import com.example.falletterbackend.falletter.entity.brick.Brick
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface BrickRepository : JpaRepository<Brick, Long> {
    fun findByUser(user: User): BrickGetCountResponse

    fun findEntityByUser(user: User): Brick
}
