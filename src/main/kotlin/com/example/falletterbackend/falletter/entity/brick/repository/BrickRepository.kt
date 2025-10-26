package com.example.falletterbackend.falletter.entity.brick.repository

import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.entity.brick.Brick
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface BrickRepository : JpaRepository<Brick, Long> {
    fun findByUser(user: User): ItemBrickGetCountResponse

    fun findEntityByUser(user: User): Brick
}
