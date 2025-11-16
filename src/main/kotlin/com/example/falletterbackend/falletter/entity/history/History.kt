package com.example.falletterbackend.falletter.entity.history

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_history")
class History(
    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "amount", nullable = false)
    val amount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: HistoryType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", columnDefinition = "BIGINT", nullable = false)
    val question: Question
) : EntityBase()
