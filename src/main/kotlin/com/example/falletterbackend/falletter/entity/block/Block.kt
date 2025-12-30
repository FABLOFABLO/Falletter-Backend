package com.example.falletterbackend.falletter.entity.block

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.block.enums.BlockType
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_block")
class Block(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: BlockType,

    @Column(name = "days")
    val days: Int? = null,

    @Column(name = "block_reason", columnDefinition = "VARCHAR(255)")
    val blockReason: String? = null,

    @Column(name = "start_date")
    val startDate: LocalDateTime? = null,

    @Column(name = "end_date")
    val endDate: LocalDateTime? = null
) : EntityBase()
