package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.exception.notice.NoticeNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeGetDetailService(
    private val noticeRepository: NoticeRepository
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): NoticeDetailsResponse {
        val notice = noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException

        return NoticeDetailsResponse(
            id = notice.id,
            title = notice.title,
            content = notice.content,
            authorName = notice.author.name,
            createdAt = notice.createdAt
        )
    }
}
