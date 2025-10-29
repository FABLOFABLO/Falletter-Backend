package com.example.falletterbackend.falletter.entity.question

import jakarta.persistence.*

@Entity
@Table(name = "tbl_question")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "question", columnDefinition = "VARCHAR(255)")
    val question: String
)
