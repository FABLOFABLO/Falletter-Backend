package com.example.falletterbackend.falletter.entity.notice

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.admin.Admin
import jakarta.persistence.*

@Entity
@Table(name = "tbl_notice")
class Notice(
    @Column(name = "title", columnDefinition = "VARCHAR(128)", nullable = false)
    val title: String,

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    val author: Admin
) : EntityBase()
