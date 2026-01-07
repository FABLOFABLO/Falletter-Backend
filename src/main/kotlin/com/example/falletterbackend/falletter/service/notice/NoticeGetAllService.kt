package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeGetAllService(
    private val noticeFacade: NoticeFacade
) {
    @Cacheable(value = ["notices"], key = "'all'")
    @Transactional(readOnly = true)
    fun execute(): List<NoticeListResponse> {
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
}
