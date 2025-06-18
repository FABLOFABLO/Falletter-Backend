package com.example.falletterbackend.falletter.entity.answer

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.question.Question
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_answer")
class Answer(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", columnDefinition = "BIGINT", nullable = false)
    val question: Question,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", columnDefinition = "BIGINT", nullable = false)
    val targetUserId: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    val writerId: User
) : EntityBase()