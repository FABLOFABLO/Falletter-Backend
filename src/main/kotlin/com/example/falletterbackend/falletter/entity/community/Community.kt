package com.example.falletterbackend.falletter.entity.community

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_community")
class Community(
    @Column(name = "title", columnDefinition = "VARCHAR(128)", nullable = false)
    val title: String,

    @Column(name = "content", columnDefinition = "VARCAHR(512)", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", columnDefinition = "BIGINT", nullable = false)
    val author : User
) : EntityBase() {
}