package com.example.falletterbackend.falletter.entity.user

import com.example.falletterbackend.common.entity.EntityBase
import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    @Column(name = "email", columnDefinition = "VARCHAR(20)", nullable = false)
    val email: String,

    @Column(name = "password", columnDefinition = "VARCHAR(20)", nullable = false)
    val password: String,

    @Column(name = "school_number", columnDefinition = "VARCHAR(4)", nullable = false)
    val schoolNumber: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "VARCHAR(10)", nullable = false)
    val gender: Gender,

    @Column(name = "profile_image", columnDefinition = "VARCHAR(255)")
    val profileImage: String
) : EntityBase() {

}