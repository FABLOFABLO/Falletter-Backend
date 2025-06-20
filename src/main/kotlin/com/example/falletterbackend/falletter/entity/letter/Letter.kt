package com.example.falletterbackend.falletter.entity.letter

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_letter")
class Letter(
    @Column(name = "letter_count", columnDefinition = "BIGINT", nullable = false)
    var letterCount: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : EntityBase()