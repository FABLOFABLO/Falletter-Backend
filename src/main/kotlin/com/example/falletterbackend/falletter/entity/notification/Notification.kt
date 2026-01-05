package com.example.falletterbackend.falletter.entity.notification

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_notification")
class Notification(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: User,

    @Column(name = "push_enabled", nullable = false)
    var pushEnabled: Boolean = true,

    @Column(name = "comment_enabled", nullable = false)
    var commentEnabled: Boolean = true,

    @Column(name = "brick_activation_enabled", nullable = false)
    var brickActivationEnabled: Boolean = true,

    @Column(name = "brick_enabled", nullable = false)
    var brickEnabled: Boolean = true,

    @Column(name = "letter_enabled", nullable = false)
    var letterEnabled: Boolean = true,

    @Column(name = "letter_sent_enabled", nullable = false)
    var letterSentEnabled: Boolean = true,

    @Column(name = "admin_notice_enabled", nullable = false)
    var adminNoticeEnabled: Boolean = true
) : EntityBase()
