package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.dto.admin.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.facade.admin.AdminFacade
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeService(
    private val noticeFacade: NoticeFacade,
    private val adminFacade: AdminFacade
) {
    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun createNotice(request: NoticeCreateRequest) {
        val admin = adminFacade.getCurrentAdmin()

        val notice = Notice(
            title = request.title,
            content = request.content,
            author = admin
        )

        noticeFacade.save(notice)
    }

    @Transactional(readOnly = true)
    fun getAllNotices(): List<NoticeListResponse> {
        return noticeFacade.getAllNotices()
            .map { NoticeListResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getNoticeDetail(id: Long): NoticeDetailsResponse {
        val notice = noticeFacade.getNoticeById(id)
        return NoticeDetailsResponse.from(notice)
    }

    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun deleteNotice(id: Long) {
        val notice = noticeFacade.getNoticeById(id)
        noticeFacade.delete(notice)
    }
}
