package com.example.falletterbackend.falletter.entity.letter

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.dto.letter.response.LetterReceivedListResponse
import com.example.falletterbackend.falletter.dto.letter.response.LetterSentListResponse
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_letter")
class Letter(
    @Column(name = "content", columnDefinition = "VARCHAR(512)", nullable = false)
    val content: String,

    @Column(name = "is_delivered", nullable = false)
    var isDelivered: Boolean = false,

    @Column(name = "is_passed", nullable = false)
    var isPassed: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id", columnDefinition = "BIGINT", nullable = false)
    val reception: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", columnDefinition = "BIGINT", nullable = false)
    val sender: User,
) : EntityBase() {
    fun toReceivedListResponse() = LetterReceivedListResponse(
        id = id,
        content = content,
        receptionId = reception.id,
        senderId = sender.id,
        isDelivered = isDelivered,
        isPassed = isPassed,
        createdAt = createdAt
    )

    fun toSentListResponse() = LetterSentListResponse(
        id = id,
        content = content,
        receptionId = reception.id,
        senderId = sender.id,
        isDelivered = isDelivered,
        isPassed = isPassed,
        createdAt = createdAt
    )
}
