package com.example.falletterbackend.falletter.entity.item

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*


@Entity
@Table(name = "tbl_item")
class Item(
    @Column(name = "brick_count", columnDefinition = "BIGINT", nullable = false)
    var brickCount: Long,

    @Column(name = "letter_count", columnDefinition = "BIGINT", nullable = false)
    var letterCount: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User
) : EntityBase(){
    fun changeBrickCount(amount: Long) {
        val newCount = this.brickCount + amount
        require(newCount >= 0) { "brickCount는 0보다 작을 수 없습니다." }
        this.brickCount = newCount
    }

    fun changeLetterCount(amount: Long) {
        val newCount = this.letterCount + amount
        require(newCount >= 0) { "brickCount는 0보다 작을 수 없습니다." }
        this.letterCount = newCount
    }
}