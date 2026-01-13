package com.example.falletterbackend.falletter.entity.item.repository

import com.example.falletterbackend.falletter.entity.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>, ItemRepositoryCustom {
}
