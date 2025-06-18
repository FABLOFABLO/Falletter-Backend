package com.example.falletterbackend.falletter.entity.question

import com.example.falletterbackend.common.entity.EntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Entity
@Table(name = "tbl_question")
class Question(
    @Column(name = "question", columnDefinition = "VARCHAR(255)")
    val question: String

) : EntityBase()