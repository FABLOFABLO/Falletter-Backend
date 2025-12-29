package com.example.falletterbackend.falletter.entity.user

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.letter.Letter
import com.example.falletterbackend.falletter.entity.user.enums.Gender
import com.example.falletterbackend.falletter.entity.user.enums.Role
import com.example.falletterbackend.falletter.entity.user.enums.Theme
import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    @Column(name = "email", columnDefinition = "VARCHAR(128)", nullable = false)
    val email: String,

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    val password: String,

    @Column(name = "school_number", columnDefinition = "VARCHAR(4)", nullable = false)
    val schoolNumber: String,

    @Column(name = "name", columnDefinition = "VARCHAR(5)", nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "VARCHAR(10)", nullable = false)
    val gender: Gender,

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", columnDefinition = "VARCHAR(20)", nullable = false)
    val theme: Theme,

    @Column(name = "profile_image", columnDefinition = "VARCHAR(255)")
    val profileImage: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "VARCHAR(20)", nullable = false)
    val role: Role = Role.USER,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<Item> = mutableListOf(),

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val letters: List<Letter> = mutableListOf()
) : EntityBase()
