package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(
    private val noticeFacade: NoticeFacade
) {
    @Cacheable(value = ["notices"], key = "'all'")
    @Transactional(readOnly = true)
    fun getAllNotices(): List<NoticeListResponse> {
        return noticeFacade.getAllNotices()
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

    @Transactional(readOnly = true)
    fun getNoticeDetail(id: Long): NoticeDetailsResponse {
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
