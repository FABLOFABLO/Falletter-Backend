package com.example.falletterbackend.falletter.entity.comment

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.community.Community
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_comment")
class Comment(
    @Column(name = "comment", columnDefinition = "VARCHAR(255)", nullable = false)
    val comment: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    val community: Community,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) : EntityBase()