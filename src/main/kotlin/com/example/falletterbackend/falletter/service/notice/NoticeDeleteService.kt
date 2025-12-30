package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.exception.notice.NoticeNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeDeleteService(
    private val noticeRepository: NoticeRepository
) {
    @Transactional
    fun execute(noticeId: Long) {
        val notice = noticeRepository.findByIdOrNull(noticeId)
            ?: throw NoticeNotFoundException

        noticeRepository.delete(notice)
    }
}
