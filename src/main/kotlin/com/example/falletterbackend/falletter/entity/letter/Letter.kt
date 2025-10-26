package com.example.falletterbackend.falletter.entity.letter

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_letter_box")
class Letter(
    @Column(name = "content", columnDefinition = "VARCHAR(512)", nullable = false)
    val content: String,

    @Column(name = "is_delivered", nullable = false)
    var isDelivered: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id", columnDefinition = "BIGINT", nullable = false)
    val reception: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    val sender: User,


) : EntityBase()