package com.example.falletterbackend.falletter.entity.brick

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.letter.RewardType
import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*


@Entity
@Table(name = "tbl_brick")
class Brick(
    @Column(name = "brick_count", columnDefinition = "BIGINT", nullable = false)
    val brickCount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", columnDefinition = "VARCHAR(10)", nullable = false)
    val rewardType: RewardType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    val question: Question
) : EntityBase()