package com.example.falletterbackend.falletter.entity.block.repository

import com.example.falletterbackend.falletter.entity.block.Block
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface BlockRepository : JpaRepository<Block, Long> {
    fun findAllByUser(user: User): List<Block>
    fun findByUserAndEndDateAfter(user: User, now: LocalDateTime): Block?
    fun countByUserAndType(user: User, type: BlockType): Long
    fun findAllByUserAndType(user: User, type: BlockType): List<Block>
}
