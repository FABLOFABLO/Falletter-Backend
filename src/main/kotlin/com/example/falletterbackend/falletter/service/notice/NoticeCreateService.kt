package com.example.falletterbackend.falletter.service.notice

import com.example.falletterbackend.falletter.dto.notice.request.NoticeCreateRequest
import com.example.falletterbackend.falletter.entity.notice.Notice
import com.example.falletterbackend.falletter.entity.notice.repository.NoticeRepository
import com.example.falletterbackend.falletter.facade.user.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeCreateService(
    private val noticeRepository: NoticeRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: NoticeCreateRequest) {
        val user = userFacade.getCurrentUser()

        val notice = Notice(
            title = request.title,
            content = request.content,
            author = user
        )

        noticeRepository.save(notice)
    }
}
