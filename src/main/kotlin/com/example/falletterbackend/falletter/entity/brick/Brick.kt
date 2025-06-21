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
    val user: User,

) : EntityBase(){
    fun increaseBrickCount(amount: Long) {
        require(amount > 0) { "추가할 letter 수는 0보다 커야 합니다." }
        this.brickCount += amount
    }
}