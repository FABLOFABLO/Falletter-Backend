package com.example.falletterbackend.falletter.entity.letter

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*


@Entity
@Table(name = "tbl_letter")
class Letter(
    @Column(name = "letter_count", columnDefinition = "BIGINT", nullable = false)
    val letterCount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", columnDefinition = "VARCHAR(10)", nullable = false)
    val rewardType: RewardType,

    @Column(name = "content", columnDefinition = "VARCHAR(512)", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id", columnDefinition = "BIGINT", nullable = false)
    val reception: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", columnDefinition = "BIGINT", nullable = false)
    val sender: User
) : EntityBase()