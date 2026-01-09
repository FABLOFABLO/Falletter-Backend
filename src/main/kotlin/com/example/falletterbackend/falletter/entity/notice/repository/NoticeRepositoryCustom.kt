package com.example.falletterbackend.falletter.entity.notice.repository

import com.example.falletterbackend.falletter.entity.notice.Notice

interface NoticeRepositoryCustom {
    fun findAllWithAuthor(): List<Notice>
}
