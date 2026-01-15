package com.example.falletterbackend.falletter.entity.terms

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_terms")
class Terms(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "service_terms", nullable = false)
    val serviceTerms: Boolean = false,

    @Column(name = "privacy_policy", nullable = false)
    val privacyPolicy: Boolean = false,

    @Column(name = "community_terms", nullable = false)
    val communityTerms: Boolean = false,

    @Column(name = "push_notification", nullable = false)
    val pushNotification: Boolean = false
) : EntityBase()
