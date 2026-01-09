package com.example.falletterbackend.falletter.service.admin.notice

import com.example.falletterbackend.common.annotation.AdminOnly
import com.example.falletterbackend.falletter.dto.admin.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeDetailsResponse
import com.example.falletterbackend.falletter.dto.admin.notice.response.NoticeListResponse
import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.exception.notice.NoticeNotFoundException
import com.example.falletterbackend.falletter.facade.notice.NoticeFacade
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminNoticeService(
    private val noticeRepository: NoticeRepository,
    private val noticeFacade: NoticeFacade,
    private val userFacade: UserFacade
) {
    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun createNotice(request: NoticeCreateRequest) {
        val user = userFacade.getCurrentUser()

        val notice = Notice(
            title = request.title,
            content = request.content,
            author = user
        )

        noticeFacade.save(notice)
    }

    @Transactional(readOnly = true)
    fun getAllNotices(): List<NoticeListResponse> {
        return noticeRepository.findAllWithAuthor()
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

    @AdminOnly
    @CacheEvict(value = ["notices"], allEntries = true)
    @Transactional
    fun deleteNotice(id: Long) {
        val notice = noticeFacade.getNoticeById(id)
        noticeFacade.delete(notice)
    }
}
