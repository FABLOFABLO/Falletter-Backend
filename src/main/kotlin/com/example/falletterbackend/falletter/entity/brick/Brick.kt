package com.example.falletterbackend.falletter.entity.brick

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*


@Entity
@Table(name = "tbl_brick")
class Brick(
    @Column(name = "brick_count", columnDefinition = "BIGINT", nullable = false)
    var brickCount: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User

) : EntityBase() {
    fun changeBrickCount(amount: Long) {
        val newCount = this.brickCount + amount
        require(newCount >= 0) { "brickCount는 0보다 작을 수 없습니다." }
        this.brickCount = newCount
    }
}