package com.example.falletterbackend.falletter.entity.user

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.item.Item
import com.example.falletterbackend.falletter.entity.letter.Letter
import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
class User(
    @Column(name = "email", columnDefinition = "VARCHAR(20)", nullable = false)
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

    @Column(name = "profile_image", columnDefinition = "VARCHAR(255)")
    val profileImage: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val items: List<Item> = mutableListOf(),

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    val letters: List<Letter> = mutableListOf()
) : EntityBase()
