package com.example.falletterbackend.falletter.entity.item.repository

import com.example.falletterbackend.falletter.dto.item.response.ItemBrickGetCountResponse
import com.example.falletterbackend.falletter.dto.item.response.ItemLetterGetCountResponse
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.user.User

interface ItemRepositoryCustom {
    fun findBrickCountByUser(user: User): ItemBrickGetCountResponse?

    fun findLetterCountByUser(user: User): ItemLetterGetCountResponse?

    fun findEntityByUser(user: User): Item?

    fun existsByUserAndLetterCountGreaterThan(user: User, count: Long): Boolean
}
