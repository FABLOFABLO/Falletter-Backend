package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeGetDetailService(
    private val noticeFacade: NoticeFacade
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): NoticeDetailsResponse {
        val notice = noticeFacade.getNoticeById(id)

        return NoticeDetailsResponse(
            id = notice.id,
            title = notice.title,
            content = notice.content,
            authorName = notice.author.name,
            createdAt = notice.createdAt
        )
    }
}
