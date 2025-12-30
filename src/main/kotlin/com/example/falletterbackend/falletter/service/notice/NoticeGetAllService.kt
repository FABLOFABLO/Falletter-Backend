package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeGetAllService(
    private val noticeRepository: NoticeRepository
) {
    @Transactional(readOnly = true)
    fun execute(): List<NoticeListResponse> {
        return noticeRepository.findAll()
            .sortedByDescending { it.createdAt }
            .map {
                NoticeListResponse(
                    id = it.id,
                    title = it.title,
                    authorName = it.author.name,
                    createdAt = it.createdAt
                )
            }
    }
}
