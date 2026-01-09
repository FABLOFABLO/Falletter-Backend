package com.example.falletterbackend.falletter.entity.notice.repository

import com.example.falletterbackend.falletter.entity.notice.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long>, NoticeRepositoryCustom
