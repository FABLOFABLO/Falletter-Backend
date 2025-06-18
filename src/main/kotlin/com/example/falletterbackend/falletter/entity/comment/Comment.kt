package com.example.falletterbackend.falletter.entity.comment

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_comment")
class Comment(
    @Column(name = "comment", columnDefinition = "VARCHAR(255)", nullable = false)
    val comment: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User
) : EntityBase()