package com.example.falletterbackend.falletter.facade.notice

import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.exception.notice.NoticeNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class NoticeFacade(
    private val noticeRepository: NoticeRepository
) {
    fun getNoticeById(id: Long): Notice {
        return noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException
    }

    fun getAllNotices(): List<Notice> {
        return noticeRepository.findAllWithAuthor()
    }

    fun save(notice: Notice): Notice {
        return noticeRepository.save(notice)
    }

    fun delete(notice: Notice) {
        noticeRepository.delete(notice)
    }
}
