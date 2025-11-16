package com.example.falletterbackend.falletter.entity.history

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_history")
class History(
    @Column(name = "title", columnDefinition = "VARCHAR(255)", nullable = false)
    val title: String,

    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    val description: String? = null,

    @Column(name = "amount", columnDefinition = "VARCHAR(16)", nullable = false)
    val amount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(32)", nullable = false)
    val type: HistoryType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", columnDefinition = "BIGINT")
    val question: Question? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    val targetUserId: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_user_id", nullable = false)
    val writerUserId: User
) : EntityBase()
