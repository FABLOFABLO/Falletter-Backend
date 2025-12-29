package com.example.falletterbackend.falletter.entity.hint

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.answer.Answer
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_hint")
class Hint (
    @Column(name = "first_hint")
    val firstHint: String?,

    @Column(name = "second_hint")
    val secondHint: String?,

    @Column(name = "third_hint")
    val thirdHint: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", columnDefinition = "BIGINT", nullable = false)
    val answer: Answer
) : EntityBase()
