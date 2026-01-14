package com.example.falletterbackend.falletter.entity.admin

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.admin.enums.AdminRole
import com.example.falletterbackend.falletter.entity.admin.enums.AdminStatus
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_admin")
class Admin(
    @Column(name = "email", columnDefinition = "VARCHAR(128)", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    var password: String,

    @Column(name = "name", columnDefinition = "VARCHAR(20)", nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "VARCHAR(10)", nullable = false)
    var gender: Gender,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(20)", nullable = false)
    var status: AdminStatus = AdminStatus.PENDING,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "VARCHAR(20)", nullable = false)
    var role: AdminRole = AdminRole.ADMIN,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    var approvedBy: Admin? = null,

    @Column(name = "processed_at")
    var processedAt: LocalDateTime? = null
) : EntityBase()
