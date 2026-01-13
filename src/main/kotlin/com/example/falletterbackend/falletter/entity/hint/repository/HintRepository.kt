package com.example.falletterbackend.falletter.entity.hint.repository

import com.example.falletterbackend.falletter.entity.hint.Hint
import org.springframework.data.jpa.repository.JpaRepository

interface HintRepository : JpaRepository<Hint, Long>, HintRepositoryCustom {
}
